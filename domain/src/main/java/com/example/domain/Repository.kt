package com.example.domain

import com.example.domain.models.News
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getNewsFromApi(category: String): Completable
    fun getNewsFromDataBase(): Observable<List<News>>
    fun getNewsList(category: String): Single<List<News>>
}