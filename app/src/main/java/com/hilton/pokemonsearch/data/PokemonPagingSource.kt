package com.hilton.pokemonsearch.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.hilton.pokemonsearch.PokemonListByNameQuery


class PokemonPagingSource (
    private val apolloClient: ApolloClient,
    val query: String
): PagingSource<Int, PokemonSpecies>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonSpecies>): Int? {
        val pos =  state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

        return pos
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonSpecies> {
        try {
            val offset = if (params.key == null) 0 else {
                params.key as Int + params.loadSize
            }
            val response = apolloClient.query(PokemonListByNameQuery(
                search = "%$query%",
                limit = params.loadSize,
                offset = offset
            )).execute()

            val nextKey = if (response.data?.pokemon_v2_pokemonspecies?.isEmpty() == true) {
                null
            } else {
                val old = params.key ?: 0
                old + params.loadSize
            }

            return LoadResult.Page(
                data = response.data?.pokemon_v2_pokemonspecies ?: emptyList(),
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}