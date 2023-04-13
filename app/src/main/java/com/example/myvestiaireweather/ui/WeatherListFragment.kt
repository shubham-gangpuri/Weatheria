package com.example.myvestiaireweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvestiaireweather.databinding.FragmentWeatherListBinding
import com.example.myvestiaireweather.domain.entity.WeatherDataEntity
import com.example.myvestiaireweather.ui.adapter.WeatherListAdapter
import com.example.myvestiaireweather.ui.ext.showError
import com.example.myvestiaireweather.ui.uistate.WeatherUIEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherListFragment : Fragment() {
  private var _binding: FragmentWeatherListBinding? = null
  private val binding
    get() = _binding!!

  private val viewModel by viewModels<WeatherVM>()

  private val adapter = WeatherListAdapter { data -> onClick(data) }

  private fun onClick(data: WeatherDataEntity) {
    val action =
      WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailsFragment(data)
    findNavController().navigate(action)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentWeatherListBinding.inflate(layoutInflater, container, false)
    setUpUI()
    subscribeObserver()
    return binding.root
  }

  private fun setUpUI() {
    viewModel.onTriggerEvent(WeatherUIEvent.GetWeatherData)
    binding.weatherList.layoutManager =
      LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
  }

  private fun subscribeObserver() {
    viewLifecycleOwner.lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.collect {
          binding.City.text = it.weatherData.city?.name

          adapter.submitList(it.weatherData.list)
          binding.weatherList.adapter = adapter
          it.isLoading

          it.errorMessage?.let { e -> context?.showError(e) { viewModel.resetState() } }
        }
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}
