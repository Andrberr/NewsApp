package com.example.data

import com.example.data.mappers.NewsMapper
import com.example.data.network.NewsService
import com.example.data.source.DataBaseSource
import com.example.data.source.UserDataSource
import com.example.domain.Repository
import com.example.domain.models.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val newsMapper: NewsMapper,
    private val userDataSource: UserDataSource,
    private val dataBaseSource: DataBaseSource
) : Repository {

    override suspend fun getNewsList(cache: Boolean): List<News> {
        return withContext(Dispatchers.IO) {
            if (cache) {
                val response =
                    service.getNewsResponse("apple", userDataSource.getUserToken()).execute().body()
                        ?: throw Exception()
                val newsList = (response.list ?: listOf()).map { newsMapper.responseToEntity(it) }
                dataBaseSource.delete(dataBaseSource.getAll())
                dataBaseSource.insertAll(newsList)
                newsList.map { newsMapper.entityToDefault(it) }
            } else dataBaseSource.getAll().map { newsMapper.entityToDefault(it) }
        }
    }

    override fun setToken(token: String) {
        userDataSource.setUserToken(token)
    }
}