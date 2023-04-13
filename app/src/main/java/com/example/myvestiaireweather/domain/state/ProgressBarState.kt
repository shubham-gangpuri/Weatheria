package com.example.myvestiaireweather.domain.state

sealed class ProgressBarState {
  object Loading : ProgressBarState()
  object Idle : ProgressBarState()
}
