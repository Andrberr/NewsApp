package com.example.data.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("author") val author: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("url") val link: String? = null,
    @SerializedName("urlToImage") val imageUrl: String? = null
)