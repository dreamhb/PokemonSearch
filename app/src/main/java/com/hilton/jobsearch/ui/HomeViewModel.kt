package com.hilton.jobsearch.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hilton.jobsearch.data.PokemonRepository
import com.hilton.jobsearch.data.PokemonSpec
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model for HomeFragment, the main logic is here.
 * We use AndroidViewModel here since we use SharedPreference to store the last 5 search
 */
class HomeViewModel @Inject constructor(
    private val repo: PokemonRepository,
    app: Application
): AndroidViewModel(app) {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Default)

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    suspend fun searchPokemon(type: String) {
        viewModelScope.launch {

            try {
                _uiState.value = UiState.Loading
                val pokemon = repo.searchPokemonByName(type)
                //_uiState.value = UiState.Success(jobs)
                _uiState.value = UiState.Default
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message)
            }
        }
    }

    companion object {
        const val TAG = "HomeVM"
    }
}

sealed class UiState {
    data class Success(
        val result: List<PokemonSpec> = emptyList()
    ) : UiState()

    data class Error(val message: String?): UiState()

    data object Loading : UiState()

    data object Default : UiState()
}