package com.example.data.network

import com.example.data.models.NewsListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("x-api-key: 5193158c81d94ee7a0ce2981761b87d9")
    @GET("everything")
    fun getNewsResponse(@Query("q") type: String): Single<NewsListResponse>
}