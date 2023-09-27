package com.hilton.jobsearch.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

/**
 * This is the repository in DATA layer.
 *
 * Because graphql api is not working, so use retrofit with mock interceptor instead.
 *
 * When GraphQL is online, we can easily replace retrofit with it.
 */
class JobSearchRepository @Inject constructor(
    private val api: GraphQLApi
) {

    suspend fun searchJob(type: String) = withContext(Dispatchers.IO) {
        api.searchJob(type)
    }
}


interface GraphQLApi {
    @GET("/{type}")
    suspend fun searchJob(@Path("type") type: String): List<Job>
}