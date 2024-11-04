package com.demo.domain.usecase

import com.demo.domain.model.Weather
import com.demo.domain.repository.WeatherRepository
import com.demo.vdemotask.core.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(private val weatherInputRepository: WeatherRepository) {

    fun execute(city: String): Flow<Resource<Weather>> {
        return weatherInputRepository.searchWeatherForCity(city)
    }
}