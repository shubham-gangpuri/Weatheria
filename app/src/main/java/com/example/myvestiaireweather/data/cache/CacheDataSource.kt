package com.example.myvestiaireweather.data.cache

import com.example.myvestiaireweather.data.cache.dao.WeatherDao
import com.example.myvestiaireweather.data.cache.model.WeatherCacheModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheDataSource @Inject constructor(private val dao:WeatherDao):CacheDataSourceContract {

    override suspend fun insertWeather(data: WeatherCacheModel) {
        dao.insertWeather(data)
    }

    override suspend fun getWeather(): Flow<WeatherCacheModel> = dao.getWeather()

    override suspend fun wipeWeatherData() {
        dao.clearWeatherData()
    }
}