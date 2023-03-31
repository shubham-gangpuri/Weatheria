package com.example.myvestiaireweather.domain

import com.example.myvestiaireweather.data.cache.model.WeatherCacheModel
import com.example.myvestiaireweather.data.remote.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepositoryContract {
    suspend fun getWeatherForecast(query:Map<String,String>):WeatherResponse
    suspend fun saveWeatherData(entity:WeatherCacheModel)
    suspend fun getCachedWeatherData(): Flow<WeatherCacheModel>
}