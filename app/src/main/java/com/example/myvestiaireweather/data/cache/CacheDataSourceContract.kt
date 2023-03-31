package com.example.myvestiaireweather.data.cache

import com.example.myvestiaireweather.data.cache.model.WeatherCacheModel
import kotlinx.coroutines.flow.Flow


 interface CacheDataSourceContract {
    suspend fun insertWeather(data: WeatherCacheModel)
    suspend fun getWeather(): Flow<WeatherCacheModel>
    suspend fun wipeWeatherData()
}