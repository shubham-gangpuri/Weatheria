package com.example.myvestiaireweather.data.cache.di

import android.app.Application
import androidx.room.Room
import com.example.myvestiaireweather.common.Constant.DB_NAME
import com.example.myvestiaireweather.data.cache.AppDatabase
import com.example.myvestiaireweather.data.cache.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DatabaseModule {
  @[Provides Singleton]
  fun providesAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

  @[Provides Singleton]
  fun providesCharacterDao(appDatabase: AppDatabase): WeatherDao {
    return appDatabase.weatherDao()
  }
}
