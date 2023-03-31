package com.example.myvestiaireweather.domain.di

import android.app.Application
import com.example.myvestiaireweather.domain.connectivity.NetworkDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class ConnectionModule {

    @[Provides Singleton]
    fun provideNetworkDetector(
        application: Application
    ):NetworkDetector{
        return NetworkDetector(application)
    }

}