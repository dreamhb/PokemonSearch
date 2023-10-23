package com.hilton.pokemonsearch.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This is the repository in DATA layer.
 *
 * Because graphql api is not working, so use retrofit with mock interceptor instead.
 *
 * When GraphQL is online, we can easily replace retrofit with it.
 */
class PokemonRepository @Inject constructor(
    private val api: ApolloClient
) {
    fun getSearchResultStream(query: String): Flow<PagingData<PokemonSpecies>> {
        return Pager(
            PagingConfig(pageSize = 6, initialLoadSize = 6)
        ) {
            PokemonPagingSource(api, query)
        }.flow
    }
}