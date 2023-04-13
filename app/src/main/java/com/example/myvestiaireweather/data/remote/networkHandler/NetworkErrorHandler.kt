package com.example.myvestiaireweather.data.remote.networkHandler

import com.example.myvestiaireweather.data.remote.model.ErrorResponse
import com.example.myvestiaireweather.domain.state.DataState
import com.example.myvestiaireweather.domain.state.UIComponent
import com.squareup.moshi.Moshi
import okio.IOException
import retrofit2.HttpException

fun <T> handleNetworkException(e: Throwable): DataState<T> {
  return when (e) {
    is IOException ->
      DataState.Error(
        UIComponent.Dialog(title = "An Error Occurred", description = e.message ?: UNKNOWN_ERROR)
      )
    is HttpException -> {
      val errorResponse = convertErrorBody(e)
      DataState.Error(
        UIComponent.Dialog(
          title = "Network Error",
          description = errorResponse?.message ?: NETWORK_ERROR
        )
      )
    }
    else -> {
      DataState.Error(UIComponent.None(description = UNKNOWN_ERROR))
    }
  }
}

private const val UNKNOWN_ERROR = "Unknown Error Occurred Restart"
private const val NETWORK_ERROR = "Network Error Retry"

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
  return try {
    throwable.response()?.errorBody()?.source()?.let {
      val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
      moshiAdapter.fromJson(it)
    }
  } catch (exception: Exception) {
    null
  }
}
