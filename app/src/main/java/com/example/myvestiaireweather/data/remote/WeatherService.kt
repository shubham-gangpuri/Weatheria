package com.example.myvestiaireweather.data.remote

import com.example.myvestiaireweather.data.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @QueryMap queries:Map<String,String>
    ):WeatherResponse

}

