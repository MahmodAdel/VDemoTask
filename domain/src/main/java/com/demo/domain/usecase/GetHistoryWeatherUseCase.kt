package com.demo.domain.usecase

import com.demo.domain.model.Weather
import com.demo.domain.repository.WeatherRepository
import com.demo.vdemotask.core.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryWeatherUseCase @Inject constructor(private val weatherInputRepository: WeatherRepository) {

    fun execute(): Flow<Resource<List<Weather>>> {
        return weatherInputRepository.getWeatherHistoryFromLocal()
    }
}