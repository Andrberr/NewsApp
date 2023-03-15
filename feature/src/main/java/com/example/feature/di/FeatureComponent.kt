package com.example.feature.di

import com.example.feature.SecondActivity
import dagger.Component

@Component(dependencies = [FeatureDependencies::class])
interface FeatureComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: FeatureDependencies): FeatureComponent
    }

    fun inject(activity: SecondActivity)
}