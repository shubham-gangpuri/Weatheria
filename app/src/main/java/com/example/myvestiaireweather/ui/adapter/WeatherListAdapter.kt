package com.example.myvestiaireweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myvestiaireweather.common.Constant.DAY_MONTH_DATE_PATTERN
import com.example.myvestiaireweather.common.Constant.IMAGE_URL
import com.example.myvestiaireweather.common.Constant.WEEK_DATE_PATTERN
import com.example.myvestiaireweather.databinding.WeatherListLayoutBinding
import com.example.myvestiaireweather.domain.entity.WeatherDataEntity
import com.example.myvestiaireweather.ui.ext.formatUnixTime
import kotlin.math.roundToLong

class WeatherListAdapter(private val onclick: (data: WeatherDataEntity) -> Unit) :
  ListAdapter<WeatherDataEntity, WeatherListAdapter.WeatherListVH>(weatherListDiff) {

  inner class WeatherListVH(private val binding: WeatherListLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: WeatherDataEntity) {
      with(binding) {
        root.setOnClickListener { onclick(data) }
        WeatherDesc.text = data.weather[0].description
        WeatherImage.load(IMAGE_URL.plus(data.weather[0].icon).plus(".png"))
        WeatherDegree.text = "${data.temp.day.roundToLong()}°C"
        DateWeek.text =
          data.timeOfForecast.toLong().formatUnixTime(WEEK_DATE_PATTERN).substring(0, 3)
        Date.text = data.timeOfForecast.toLong().formatUnixTime(DAY_MONTH_DATE_PATTERN)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListVH {
    return WeatherListVH(
      WeatherListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: WeatherListVH, position: Int) {
    val data = getItem(position)
    holder.bind(data)
  }

  companion object {
    val weatherListDiff =
      object : DiffUtil.ItemCallback<WeatherDataEntity>() {
        override fun areItemsTheSame(
          oldItem: WeatherDataEntity,
          newItem: WeatherDataEntity
        ): Boolean {
          return oldItem == newItem
        }

        override fun areContentsTheSame(
          oldItem: WeatherDataEntity,
          newItem: WeatherDataEntity
        ): Boolean {
          return oldItem.pop == newItem.pop
        }
      }
  }
}
