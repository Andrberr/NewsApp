package com.example.domain

import com.example.domain.models.News
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNewsListFlow(category: String): Flow<List<News>>
    suspend fun getNewsList(category: String): List<News>
}