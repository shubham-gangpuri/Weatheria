package com.example.myvestiaireweather.domain.state

sealed class UIComponent {
  data class Dialog(val title: String, val description: String) : UIComponent()

  data class None(val description: String) : UIComponent()
}
