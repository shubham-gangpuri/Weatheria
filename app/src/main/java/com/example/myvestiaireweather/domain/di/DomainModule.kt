package com.example.myvestiaireweather.domain.di

import com.example.myvestiaireweather.domain.AsyncDispatcher
import com.example.myvestiaireweather.domain.DefaultAsyncDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface DomainModule {
  @get:[Binds Singleton]
  val DefaultAsyncDispatcher.dipatcher: AsyncDispatcher
}
