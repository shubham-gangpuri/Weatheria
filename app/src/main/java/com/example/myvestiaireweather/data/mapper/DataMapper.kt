package com.example.myvestiaireweather.data.mapper

import com.example.myvestiaireweather.data.cache.model.*
import com.example.myvestiaireweather.data.remote.model.*
import com.example.myvestiaireweather.domain.entity.*

/* Remote to Cache Model */
internal fun WeatherResponse.toWeatherCache() =
  WeatherCacheModel(
    city = CityCacheModel(country = city.country, name = city.name),
    cnt = cnt,
    weatherData = list.map { it.weatherDataCache() }
  )

private fun Weather.weatherDataCache() =
  WeatherDataCacheModel(
    clouds = clouds,
    degree = deg,
    timeOfForecast = dt,
    feels_like =
      FeelsLikeCacheModel(
        day = feels_like.day,
        evening = feels_like.eve,
        morning = feels_like.morn,
        night = feels_like.night
      ),
    sunset = sunset,
    gust = gust,
    humidity = humidity,
    pop = pop,
    pressure = pressure,
    speed = speed,
    sunrise = sunrise,
    temp =
      Temp(
        day = temp.day,
        eve = temp.day,
        max = temp.max,
        min = temp.min,
        morn = temp.morn,
        night = temp.night
      ),
    weatherInfo = weatherInfo.map { it.weatherInfoCache() },
  )

private fun WeatherInfo.weatherInfoCache() =
  WeatherInfoCacheModel(id = id, main = main, description = description, icon = icon)

/** cache to domain * */
internal fun WeatherCacheModel.toWeatherEntity() =
  WeatherEntity(
    city = City(country = city.country, name = city.name),
    cnt = cnt,
    list = weatherData.map { it.weatherDataEntity() }
  )

private fun WeatherDataCacheModel.weatherDataEntity() =
  WeatherDataEntity(
    clouds = clouds,
    degree = degree,
    timeOfForecast = timeOfForecast,
    feelLike =
      FeelsLikeEntity(
        day = feels_like.day,
        eve = feels_like.evening,
        morn = feels_like.morning,
        night = feels_like.night
      ),
    sunset = sunset,
    gust = gust,
    humidity = humidity,
    pop = pop,
    pressure = pressure,
    speed = speed,
    sunrise = sunrise,
    temp =
      Temp(
        day = temp.day,
        eve = temp.day,
        max = temp.max,
        min = temp.min,
        morn = temp.morn,
        night = temp.night
      ),
    weather = weatherInfo.map { it.weatherInfoEntity() },
  )

private fun WeatherInfoCacheModel.weatherInfoEntity() =
  WeatherInfoEntity(id = id, main = main, description = description, icon = icon)
