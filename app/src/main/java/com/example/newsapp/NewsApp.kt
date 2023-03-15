package com.example.newsapp

import android.app.Application
import com.example.core.HasDependencies
import com.example.newsapp.di.ApplicationComponent
import com.example.newsapp.di.DaggerApplicationComponent

class NewsApp : Application(), HasDependencies{
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override val dependencies: Any
        get() = appComponent
}