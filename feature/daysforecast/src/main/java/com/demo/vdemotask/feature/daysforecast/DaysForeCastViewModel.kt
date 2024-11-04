package com.demo.vdemotask.feature.daysforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.model.Weather
import com.demo.domain.usecase.GetDaysForeCastWeatherUseCase
import com.demo.domain.usecase.GetHistoryWeatherUseCase
import com.demo.vdemotask.core.common.Resource
import com.demo.vdemotask.core.viewModel.BaseViewModel
import com.demo.vdemotask.feature.daysforecast.contract.DaysForeCastContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaysForeCastViewModel @Inject constructor(
    private val getDaysForeCastWeatherUseCase: GetDaysForeCastWeatherUseCase
) : BaseViewModel<DaysForeCastContract.Event,DaysForeCastContract.State,DaysForeCastContract.Effect>() {

    fun initData(lat:String,lon:String) {
        loadHistory(lat,lon)
    }

    private fun loadHistory(lat: String,lon: String) {
        viewModelScope.launch {
            getDaysForeCastWeatherUseCase.execute(lat,lon).collect {
                when(it){
                    is Resource.Error -> {
                        //error msg
                        setEffect {
                            DaysForeCastContract.Effect.ShowError(message = it.exception.message)
                        }
                    }
                    is Resource.InitScreen -> TODO()
                    Resource.Loading -> TODO()
                    is Resource.Success -> {
                        setState {
                            copy(
                                fetchDataState = DaysForeCastContract.FetchDataState.Success(
                                    it.data
                                )
                            )
                        }

                    }
                }
            }
        }
    }

    override fun createInitialState(): DaysForeCastContract.State {
        return DaysForeCastContract.State(
            fetchDataState = DaysForeCastContract.FetchDataState.Idle,
        )
    }

    override fun handleEvent(event: DaysForeCastContract.Event) {
        when(event){
            is DaysForeCastContract.Event.fetchData -> {
                loadHistory(event.lat,event.lon)

            }
        }
    }
}