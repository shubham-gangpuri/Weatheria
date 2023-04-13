package com.example.myvestiaireweather.domain.entity

import com.example.myvestiaireweather.data.remote.model.Temp
import java.io.Serializable

data class WeatherDataEntity(
  val clouds: Int,
  val degree: Int,
  val timeOfForecast: Int,
  val feelLike: FeelsLikeEntity,
  val gust: Double,
  val humidity: Int,
  val pop: Double,
  val pressure: Int,
  val speed: Double,
  val sunrise: Int,
  val sunset: Int,
  val temp: Temp,
  val weather: List<WeatherInfoEntity>
) : Serializable
