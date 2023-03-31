package com.example.myvestiaireweather.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myvestiaireweather.data.cache.model.WeatherCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(entity: WeatherCacheModel)

    @Query("SELECT * FROM WEATHER_TABLE")
    fun getWeather(): Flow<WeatherCacheModel>

    @Query("DELETE FROM WEATHER_TABLE")
    suspend fun clearWeatherData()
}