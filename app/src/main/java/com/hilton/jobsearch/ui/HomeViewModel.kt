package com.hilton.jobsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilton.jobsearch.data.PokemonRepository
import com.hilton.jobsearch.data.PokemonSpecies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

        viewModelScope.launch {
            try {
                //we show loading first
                _uiState.emit(UiState.Loading)
                delay(2000)

                repo.getSearchResultStream(query).cachedIn(viewModelScope).collectLatest { pagingData ->
                    _uiState.update {
                        //show results when succeed
                        UiState.Success(pagingData)
                    }
                }
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e.message))
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