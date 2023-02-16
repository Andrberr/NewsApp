package com.example.newsapp.domain.models

data class News(
    val author: String,
    val title: String,
    val description: String,
    val link: String,
    val imageUrl: String
)