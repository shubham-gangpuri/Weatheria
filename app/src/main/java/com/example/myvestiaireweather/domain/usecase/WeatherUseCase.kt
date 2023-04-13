package com.example.myvestiaireweather.domain.usecase

import com.example.myvestiaireweather.data.mapper.toWeatherCache
import com.example.myvestiaireweather.data.mapper.toWeatherEntity
import com.example.myvestiaireweather.data.remote.networkHandler.handleNetworkException
import com.example.myvestiaireweather.domain.AsyncDispatcher
import com.example.myvestiaireweather.domain.WeatherRepositoryContract
import com.example.myvestiaireweather.domain.connectivity.NetworkDetector
import com.example.myvestiaireweather.domain.entity.WeatherEntity
import com.example.myvestiaireweather.domain.state.DataState
import com.example.myvestiaireweather.domain.state.ProgressBarState
import javax.inject.Inject
import kotlinx.coroutines.flow.*

class WeatherUseCase
@Inject
constructor(
  private val weatherRepository: WeatherRepositoryContract,
  private val dispatcher: AsyncDispatcher,
  private val networkDetector: NetworkDetector
) {
  operator fun invoke(): Flow<DataState<WeatherEntity>> =
    flow {
        val queryMap =
          mapOf(
            "q" to CITY,
            "mode" to RESPONSE_TYPE,
            "units" to UNIT,
            "cnt" to COUNT,
            "APPID" to APP_ID
          )
        // emit last saved data from db and update on data from the internet
        val data = weatherRepository.getCachedWeatherData().firstOrNull()
        data?.let { emit(DataState.Data(data = it.toWeatherEntity())) }

        if (networkDetector.isConnected()) {
          // emit Loading state
          emit(DataState.Loading(ProgressBarState.Loading))
          // fetch from network
          val weatherResponse = weatherRepository.getWeatherForecast(queryMap)

          // save into database
          weatherRepository.saveWeatherData(weatherResponse.toWeatherCache())
        }
        val cache = weatherRepository.getCachedWeatherData().first()
        // emit data
        emit(DataState.Data(cache.toWeatherEntity()))
      }
      .catch { exception -> emit(handleNetworkException(exception)) }
      .flowOn(dispatcher.io)

  private companion object {
    const val APP_ID = "648a3aac37935e5b45e09727df728ac2"
    const val COUNT = "16"
    const val CITY = "PARIS"
    const val RESPONSE_TYPE = "json"
    const val UNIT = "metric"
  }
}
