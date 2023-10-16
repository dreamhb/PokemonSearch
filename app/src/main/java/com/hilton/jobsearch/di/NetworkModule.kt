package com.hilton.jobsearch.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val SERVER_URL = "https://beta.pokeapi.co/graphql/v1beta"

    /**
     * network module
     * use Retrofit since GraphQL is down, otherwise will use Apollo for graphql networking
     */
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(SERVER_URL)
            .build()
    }
}