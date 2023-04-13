package com.example.myvestiaireweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myvestiaireweather.domain.state.DataState
import com.example.myvestiaireweather.domain.state.ProgressBarState
import com.example.myvestiaireweather.domain.usecase.WeatherUseCase
import com.example.myvestiaireweather.ui.uistate.WeatherUIEvent
import com.example.myvestiaireweather.ui.uistate.WeatherUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherVM @Inject constructor(private val weatherUseCase: WeatherUseCase) : ViewModel() {

  private val _uiState = MutableStateFlow(WeatherUIState())
  val uiState: StateFlow<WeatherUIState>
    get() = _uiState.asStateFlow()

  /** UI EVENT Trigger */
  fun onTriggerEvent(event: WeatherUIEvent) {
    when (event) {
      WeatherUIEvent.GetWeatherData -> {
        fetchWeatherData()
      }
    }
  }

  /** UI state reduction */
  private fun fetchWeatherData() {
    viewModelScope.launch {
      weatherUseCase().collect { dataState ->
        when (dataState) {
          is DataState.Data -> {
            _uiState.update { it.copy(weatherData = dataState.data) }
          }
          is DataState.Error -> {
            _uiState.update { it.copy(errorMessage = dataState.uiComponent) }
          }
          is DataState.Loading -> {
            _uiState.update { it.copy(isLoading = dataState.progressBarState) }
          }
        }
      }
    }
  }

  fun resetState() {
    _uiState.update { it.copy(errorMessage = null, isLoading = ProgressBarState.Idle) }
  }
}
