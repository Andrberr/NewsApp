package com.example.domain

import com.example.domain.models.News

interface Repository {
    suspend fun getNewsList(cache: Boolean, category: String): List<News>
    fun setToken(token: String)
}