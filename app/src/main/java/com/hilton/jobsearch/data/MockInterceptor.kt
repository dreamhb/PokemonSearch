package com.hilton.jobsearch.data

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * We use MockInterceptor to mock the response, if the api is working, can use it directly
 */
class MockInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message("request is mocked for testing")
            .body(ResponseBody.create(
                MediaType.parse("application/json"),
                Gson().toJson(mockResponseData)
            ))
            .addHeader("content-type", "application/json")
            .build()
    }


    private val mockResponseData: List<Job> = listOf(
        Job(
            title = "DevOps",
            company = Company("Google", "https://www.google.com"),
            description = "This is a full time position"
        ),
        Job(
            title = "Android Developer",
            company = Company("Google", "https://www.google.com"),
            description = "This is a full time position"
        ),
        Job(
            title = "iOS Developer",
            company = Company("Google", "https://www.google.com"),
            description = "This is a full time position"
        ),
        Job(
            title = "iOS Developer",
            company = Company("Hilton Group", "https://www.google.com"),
            description = "This is a full time position"
        ),
        Job(
            title = "Full Stack Engineer",
            company = Company("GraphQL", "https://www.google.com"),
            description = "This is a full time position"
        ),
        Job(
            title = "GraphQL Developer",
            company = Company("Segment", "https://www.google.com"),
            description = "At Segment, we believe companies should be able to send their customer data wherever they want, whenever they want, with no fuss. We make this easy with a single pipeline that collects, stores, filters, transforms, and sends data to hundreds of business tools with the flip of a switch. Historically, we’ve built integrations with more than 250 different customer data tools ourselves(think Mixpanel, Google Analytics, Stripe).This March, we opened up our Developer Center. For the first time, new companies could build integrations upon Segment data, using our self-service workflow. In that time, we’ve onboarded 60 separate companies, each of whom built endpoints to work with our spec. We're now looking for a Senior Fullstack Engineer to help us expand our platform…we want to offer every kind of developer (partners, customers, and indie devs) the means to use Segment data."
        ),
    )

}