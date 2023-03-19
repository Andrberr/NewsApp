package com.example.domain

import com.example.domain.models.News

interface Repository {
    suspend fun getNewsList(cache: Boolean): List<News>
    fun setToken(token: String)
}