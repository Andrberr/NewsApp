package com.example.data.source

import com.example.data.database.NewsDao
import com.example.data.database.NewsEntity
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val dao: NewsDao
) {
    fun getAll() = dao.getAll()

    fun insertAll(news: List<NewsEntity>) =
        dao.insertAll(news)

    fun deleteAll() = dao.deleteAll()
}