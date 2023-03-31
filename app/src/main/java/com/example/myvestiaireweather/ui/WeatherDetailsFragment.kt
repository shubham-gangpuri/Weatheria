package com.example.myvestiaireweather.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.myvestiaireweather.R
import com.example.myvestiaireweather.common.Constant
import com.example.myvestiaireweather.common.Constant.STANDARD_DATE_PATTERN
import com.example.myvestiaireweather.databinding.FragmentWeatherDetailsBinding
import com.example.myvestiaireweather.databinding.FragmentWeatherListBinding
import com.example.myvestiaireweather.ui.ext.formatUnixTime
import kotlin.math.roundToLong


class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding?=null
    private val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentWeatherDetailsBinding.inflate(layoutInflater,container,false)
        getWeatherData()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun getWeatherData() {
      arguments?.let { it ->
          val weatherData= WeatherDetailsFragmentArgs.fromBundle(it).WeatherData
          weatherData?.let { weatherData->
              with(binding){
                  weatherDetails.text = weatherData.weather[0].description
                  Weatherimage.load(Constant.IMAGE_URL.plus(weatherData.weather[0].icon).plus(".png"))
                  currentTemp.text = "${weatherData.temp.day.roundToLong()}°C"
                  currentlyValue.text = "${weatherData.temp.day.roundToLong()}°C"
                  feelsLikeValue.text = "${weatherData.feelLike.day.roundToLong()}°C"
                  humidityValue.text = "${weatherData.humidity} RH"
                  sunsetValue.text = weatherData.sunrise.toLong().formatUnixTime(STANDARD_DATE_PATTERN)
                  suriseValue.text = weatherData.sunset.toLong().formatUnixTime(STANDARD_DATE_PATTERN)
                  pressureValue.text = "${weatherData.pressure} p"
              }
          }
      }
    }

}