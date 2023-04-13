package com.example.myvestiaireweather.data.remote.model

import com.squareup.moshi.Json

data class ErrorResponse(@Json(name = "cod") val code: String, val message: String)
