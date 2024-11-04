package com.demo.vdemotask.feature.currentweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.model.Weather
import com.demo.domain.usecase.GetHistoryWeatherUseCase
import com.demo.vdemotask.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryWeatherViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryWeatherUseCase
) : ViewModel() {
    private val _weatherList = MutableStateFlow<List<Weather>>(emptyList())
    val weatherList: StateFlow<List<Weather>> = _weatherList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            getHistoryUseCase.execute().collect {
                when(it){
                    is Resource.Error -> {
                        //error msg
                        _isLoading.value = false
                    }
                    is Resource.InitScreen -> TODO()
                    Resource.Loading -> TODO()
                    is Resource.Success -> {
                        _weatherList.value = it.data
                        _isLoading.value = false

                    }
                }
            }
        }
    }
}