package com.example.newsapp.data

import com.example.newsapp.data.mappers.NewsMapper
import com.example.newsapp.data.network.NewsService
import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.models.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsMapper,
) : Repository {

    override suspend fun getNewsList(): List<News> {
        return withContext(Dispatchers.IO) {
            val response = service.getNewsResponse("apple").execute().body() ?: throw Exception()
            response.map{mapper(it)}
        }
    }
}