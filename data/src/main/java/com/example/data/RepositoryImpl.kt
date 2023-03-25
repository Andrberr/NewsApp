package com.example.data

import com.example.data.mappers.NewsMapper
import com.example.data.network.NewsService
import com.example.data.source.DataBaseSource
import com.example.data.source.UserDataSource
import com.example.domain.Repository
import com.example.domain.models.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val newsMapper: NewsMapper,
    private val userDataSource: UserDataSource,
    private val dataBaseSource: DataBaseSource
) : Repository {

    override suspend fun getNewsListFlow(category: String): Flow<List<News>> {
        return withContext(Dispatchers.IO) {
            val response =
                service.getNewsResponse(category).execute()
                    .body()
                    ?: throw Exception()
            val newsList = (response.list ?: listOf()).map { newsMapper.responseToEntity(it) }
            dataBaseSource.getAll().map {
                dataBaseSource.delete(it)
            }
            dataBaseSource.insertAll(newsList)
//                 newsList.map { newsMapper.entityToDefault(it) }
//            } else dataBaseSource.getAll().map { newsMapper.entityToDefault(it) }
            subscribeFlow()
        }
    }

    override suspend fun getNewsList(category: String): List<News> {
        return withContext(Dispatchers.IO) {
            val response =
                service.getNewsResponse(category).execute()
                    .body()
                    ?: throw Exception()
            val newsList = (response.list ?: listOf()).map { newsMapper.responseToEntity(it) }
            newsList.map { newsMapper.entityToDefault(it) }
        }
    }

    private suspend fun subscribeFlow(): Flow<List<News>>{
        return dataBaseSource.getAll().map { list ->
            list.map { newsMapper.entityToDefault(it) }
        }
    }
}