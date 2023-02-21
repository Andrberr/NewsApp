package com.example.newsapp.data.network

import com.example.newsapp.data.models.NewsListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    //@Headers("x-api-key: 5193158c81d94ee7a0ce2981761b87d9")
    @GET("everything")
    fun getNewsResponse(@Query("q") type: String, @Header("x-api-key") token: String): Call<NewsListResponse>
}