package com.example.data

import com.example.data.mappers.NewsMapper
import com.example.data.network.NewsService
import com.example.data.source.DataBaseSource
import com.example.domain.Repository
import com.example.domain.models.News
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val newsMapper: NewsMapper,
    private val dataBaseSource: DataBaseSource
) : Repository {

    override fun getNewsFromApi(category: String): Completable {
        return service.getNewsResponse(category)
            .flatMapCompletable {
                val newsList =
                    it.list?.map { elem -> newsMapper.responseToEntity(elem) } ?: emptyList()
                dataBaseSource.insertAll(newsList)
                Completable.complete()
            }
    }

    override fun getNewsFromDataBase(): Observable<List<News>> {
        return dataBaseSource.getAll().map { it.map { elem -> newsMapper.entityToDefault(elem) } }
    }

    override fun getNewsList(category: String): Single<List<News>> {
        return service.getNewsResponse(category)
            .map {
                val newsList =
                    (it.list ?: listOf()).map { elem -> newsMapper.responseToEntity(elem) }
                newsList.map { elem -> newsMapper.entityToDefault(elem) }
            }
    }
}