package com.example.feature.di

import com.example.domain.Repository

interface FeatureDependencies {
    fun repository(): Repository
}