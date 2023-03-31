package com.example.myvestiaireweather.data.cache.di

import com.example.myvestiaireweather.data.cache.CacheDataSource
import com.example.myvestiaireweather.data.cache.CacheDataSourceContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface DataSourceModule {
    @Binds
    fun provideCacheDataSource(cacheDataSource: CacheDataSource) : CacheDataSourceContract
}