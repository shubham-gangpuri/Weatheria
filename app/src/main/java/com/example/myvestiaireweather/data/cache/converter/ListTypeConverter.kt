package com.example.myvestiaireweather.data.cache.converter

import androidx.room.TypeConverter
import com.example.myvestiaireweather.data.cache.model.WeatherDataCacheModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

internal class ListTypeConverter {

  private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

  private val weatherDataAdapter: JsonAdapter<List<WeatherDataCacheModel>> by lazy {
    val type = Types.newParameterizedType(List::class.java, WeatherDataCacheModel::class.java)
    moshi.adapter(type)
  }

  @TypeConverter
  fun fromWeatherList(value: List<WeatherDataCacheModel>): String {
    return weatherDataAdapter.toJson(value)
  }

  @TypeConverter
  fun toWeatherList(value: String): List<WeatherDataCacheModel> {
    return weatherDataAdapter.fromJson(value) ?: emptyList()
  }
}
