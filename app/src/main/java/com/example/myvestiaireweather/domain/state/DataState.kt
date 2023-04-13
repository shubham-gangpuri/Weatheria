package com.example.myvestiaireweather.domain.state

sealed class DataState<out T> {
  class Data<T>(val data: T) : DataState<T>()

  data class Loading<T>(val progressBarState: ProgressBarState = ProgressBarState.Idle) :
    DataState<T>()

  data class Error<T>(val uiComponent: UIComponent) : DataState<T>()
}
