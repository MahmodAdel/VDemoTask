package com.demo.vdemotask.feature.daysforecast.contract

import com.demo.domain.model.Weather
import com.demo.vdemotask.core.contract.UiEffect
import com.demo.vdemotask.core.contract.UiEvent
import com.demo.vdemotask.core.contract.UiState

class DaysForeCastContract {
    sealed class Event : UiEvent {
        data class fetchData(var lat:String,var lon:String): Event()


    }
    data class State(
        val fetchDataState:FetchDataState,
    ) : UiState
    sealed class FetchDataState {
        object Idle :FetchDataState()
        object Finishing :FetchDataState()
        object Loading : FetchDataState()
        data class Success(val weather: List<Weather>) : FetchDataState()
    }

    sealed class Effect : UiEffect {

        data class ShowError(val message : String?) : Effect()

    }
}