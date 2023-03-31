package com.example.myvestiaireweather.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myvestiaireweather.common.Constant.WEATHER_TABLE
import com.example.myvestiaireweather.data.remote.model.*

@Entity(tableName = WEATHER_TABLE)
data class WeatherCacheModel(
    @Embedded val city: CityCacheModel,
    @PrimaryKey(autoGenerate = false) val cnt:Int,
    val weatherData: List<WeatherDataCacheModel>
)

    data class CityCacheModel(
        @ColumnInfo("country") val country: String,
        @ColumnInfo("name") val name: String,
    )

data class WeatherDataCacheModel(
    @ColumnInfo("clouds") val clouds: Int,
    @ColumnInfo("deg") val degree: Int,
    @ColumnInfo("dt") val timeOfForecast: Int,
    @Embedded val feels_like: FeelsLikeCacheModel,
    @ColumnInfo(name = "gust") val gust: Double,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "pop") val pop: Double,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "speed") val speed: Double,
    @ColumnInfo(name = "sunrise") val sunrise: Int,
    @ColumnInfo(name = "sunset") val sunset: Int,
    @ColumnInfo(name = "temp") val temp: Temp,
    @ColumnInfo(name = "weather")
    @Embedded val weatherInfo: List<WeatherInfoCacheModel>
)

data class FeelsLikeCacheModel(
    @ColumnInfo("day")  val day: Double,
    @ColumnInfo("eve")  val evening: Double,
    @ColumnInfo("morn")  val morning: Double,
    @ColumnInfo("night") val night: Double
)

data class WeatherInfoCacheModel(
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("main") val main: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("icon") val icon: String
)

