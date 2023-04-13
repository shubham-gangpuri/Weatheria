package com.example.myvestiaireweather.data.repository.di

import com.example.myvestiaireweather.data.repository.WeatherRepository
import com.example.myvestiaireweather.domain.WeatherRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  /** Provide Provide Dependencies for [WeatherRepository] */
  @Binds
  abstract fun provideWeatherRepository(repository: WeatherRepository): WeatherRepositoryContract
}
