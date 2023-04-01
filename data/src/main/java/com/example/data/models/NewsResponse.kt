package com.example.data.models
import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "author") val author: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "url") val link: String? = null,
    @Json(name = "urlToImage") val imageUrl: String? = null
)