package com.example.newsapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.database.NewsDao
import com.example.data.database.NewsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase( context: Context): NewsDataBase {
        return Room.databaseBuilder(context, NewsDataBase::class.java, "news_table")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: NewsDataBase): NewsDao = db.getNewsDao()
}