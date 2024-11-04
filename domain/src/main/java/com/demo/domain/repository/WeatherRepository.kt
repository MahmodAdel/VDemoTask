package com.demo.domain.repository

import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

     fun searchWeatherForCity(city:String): Flow<Resource<Weather>>
     fun getWeatherHistoryFromLocal(): Flow<Resource<List<Weather>>>

     fun searchSevenDaysWeather(lat: String,lon:String):Flow<Resource<List<Weather>>>

}