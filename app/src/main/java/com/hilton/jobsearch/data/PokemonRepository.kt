package com.hilton.jobsearch.data

import com.apollographql.apollo3.ApolloClient
import com.hilton.jobsearch.PokemonListByNameQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    suspend fun searchPokemonByName(search: String, limit: Int = 5, offset: Int = 0) = withContext(Dispatchers.IO) {
        api.query(PokemonListByNameQuery(
            "%$search%", limit, offset
        )).execute()
    }
}