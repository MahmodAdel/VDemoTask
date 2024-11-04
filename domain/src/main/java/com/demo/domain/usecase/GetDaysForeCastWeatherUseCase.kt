package com.demo.domain.usecase

import android.icu.text.SymbolTable
import com.demo.domain.model.Weather
import com.demo.domain.repository.WeatherRepository
import com.demo.vdemotask.core.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDaysForeCastWeatherUseCase @Inject constructor(private val weatherInputRepository: WeatherRepository) {

    fun execute(lat:String,lon:String): Flow<Resource<List<Weather>>> {
        return weatherInputRepository.searchSevenDaysWeather(lat,lon)
    }
}