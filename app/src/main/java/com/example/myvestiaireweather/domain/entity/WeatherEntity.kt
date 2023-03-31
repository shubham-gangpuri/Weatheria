package com.example.myvestiaireweather.domain.entity

import com.example.myvestiaireweather.data.remote.model.City

data class WeatherEntity(
    val city: City?=null,
    val cnt: Int?=null,
    val list: List<WeatherDataEntity> = listOf()
)


