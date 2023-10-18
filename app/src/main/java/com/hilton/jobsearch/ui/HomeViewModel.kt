package com.hilton.jobsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilton.jobsearch.data.PokemonRepository
import com.hilton.jobsearch.data.PokemonSpecies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model for HomeFragment, the main logic is here.
 * We use AndroidViewModel here since we use SharedPreference to store the last 5 search
 * This is a shared view model between the fragments
 */
class HomeViewModel @Inject constructor(
    private val repo: PokemonRepository,
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Default)

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    suspend fun searchPokemon(query: String) {
        _uiState.update {
            UiState.Loading
        }
        viewModelScope.launch {
            repo.getSearchResultStream(query).cachedIn(viewModelScope).catch { e ->
                _uiState.update {
                    UiState.Error(e.message)
                }
            }.collect { pagingData ->
                _uiState.update {
                    UiState.Success(pagingData)
                }
            }
        }
    }

    companion object {
        const val TAG = "HomeVM"
    }
}

sealed class UiState {
    /**
     * shown when successfully got results
     */
    data class Success(
        val result: PagingData<PokemonSpecies>
    ) : UiState()

    /**
     * shown if there is an error
     */
    data class Error(val message: String?): UiState()

    /**
     * show loading only
     */
    data object Loading : UiState()

    /**
     * show search box and button
     */
    data object Default : UiState()
}