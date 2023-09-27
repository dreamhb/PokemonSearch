package com.hilton.jobsearch.di

import com.hilton.jobsearch.data.GraphQLApi
import com.hilton.jobsearch.data.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.graphql.jobs"

    /**
     * network module
     * use Retrofit since GraphQL is down, otherwise will use Apollo for graphql networking
     */
    @Provides
    fun provideGraphQlApi(): GraphQLApi {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GraphQLApi::class.java)
    }
}