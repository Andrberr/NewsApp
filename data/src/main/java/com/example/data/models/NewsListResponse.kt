package com.example.data.models

import com.squareup.moshi.Json

data class NewsListResponse(@Json(name = "articles") val list: List<NewsResponse>? = null)