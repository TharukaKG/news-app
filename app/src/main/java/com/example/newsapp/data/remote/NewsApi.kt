package com.example.newsapp.data.remote

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.models.responses.HeadLineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<HeadLineResponse>

}
