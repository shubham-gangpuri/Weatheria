package com.example.myvestiaireweather.ui.uistate

/**
 * UI state event Users-Intent
 */
sealed class WeatherUIEvent {
    object GetWeatherData:WeatherUIEvent()
}
