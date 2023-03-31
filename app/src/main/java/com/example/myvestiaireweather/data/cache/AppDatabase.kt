package com.example.myvestiaireweather.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myvestiaireweather.data.cache.converter.ListTypeConverter
import com.example.myvestiaireweather.data.cache.dao.WeatherDao
import com.example.myvestiaireweather.data.cache.model.WeatherCacheModel

@Database(entities = [WeatherCacheModel::class], version = 1, exportSchema = false)
@TypeConverters(
    ListTypeConverter::class
)
abstract class AppDatabase:RoomDatabase(){
    abstract fun weatherDao() : WeatherDao
}