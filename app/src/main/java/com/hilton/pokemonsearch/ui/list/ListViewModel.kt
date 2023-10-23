package com.hilton.pokemonsearch.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hilton.pokemonsearch.data.PokemonRepository
import com.hilton.pokemonsearch.data.PokemonSpecies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun searchPokemon(query: String): Flow<PagingData<PokemonSpecies>> =
        withContext(Dispatchers.IO) {
            repository.getSearchResultStream(query).cachedIn(viewModelScope)
        }
}