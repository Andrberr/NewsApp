package com.example.data.database

import androidx.room.*
import androidx.room.Dao
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): Observable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)

    @Query("DELETE FROM news_table")
    fun deleteAll(): Completable
}