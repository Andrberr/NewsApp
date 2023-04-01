package com.example.newsapp.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.data.network.NewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun getRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun getNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Provides
    @Singleton
    fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    fun providePrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
    }

    companion object {
        private const val PREFS_KEY = "prefs_key"
    }
}

