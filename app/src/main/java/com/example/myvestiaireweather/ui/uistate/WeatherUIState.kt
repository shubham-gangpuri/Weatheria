package com.example.myvestiaireweather.ui.uistate

import com.example.myvestiaireweather.domain.entity.WeatherEntity
import com.example.myvestiaireweather.domain.state.ProgressBarState
import com.example.myvestiaireweather.domain.state.UIComponent

/**
 * UI state representation of the screen-state
 */
data class WeatherUIState(
    val isLoading: ProgressBarState = ProgressBarState.Idle,
    val weatherData:WeatherEntity = WeatherEntity(),
    val errorMessage: UIComponent?=null,
)