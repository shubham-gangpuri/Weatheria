package com.example.myvestiaireweather.data.remote.model

import com.squareup.moshi.Json

/**
 * Network representation of [WeatherResponse]
 */
data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val list: List<Weather>
)

data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Weather(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    @Json(name = "weather") val weatherInfo: List<WeatherInfo>
)

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)

data class City(
    val country: String,
    val name: String,
)