package com.example.myvestiaireweather.data.repository

import com.example.myvestiaireweather.data.cache.dao.WeatherDao
import com.example.myvestiaireweather.data.cache.model.WeatherCacheModel
import com.example.myvestiaireweather.data.remote.WeatherService
import com.example.myvestiaireweather.data.remote.model.WeatherResponse
import com.example.myvestiaireweather.domain.WeatherRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
   private val weatherService: WeatherService,
   private val dao: WeatherDao,
): WeatherRepositoryContract {

   override suspend fun getWeatherForecast(query: Map<String, String>): WeatherResponse {
      return weatherService.getWeatherForecast(query)
   }

   override suspend fun saveWeatherData(entity: WeatherCacheModel) {
      dao.insertWeather(entity)
   }

   override suspend fun getCachedWeatherData(): Flow<WeatherCacheModel> {
      return dao.getWeather()
   }

}