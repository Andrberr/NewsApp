package com.example.newsapp.di

import android.content.Context
import com.example.feature.di.FeatureDependencies
import com.example.newsapp.di.modules.DataBaseModule
import com.example.newsapp.di.modules.NetworkModule
import com.example.newsapp.di.modules.RepositoryModule
import com.example.newsapp.di.modules.ViewModelModule
import com.example.newsapp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataBaseModule::class, NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent: FeatureDependencies{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}