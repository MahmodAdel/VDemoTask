package com.demo.data.repository

import com.demo.data.remote.network.ApiLocationResponse
import com.demo.data.remote.network.ApiResponse
import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteRepository {

    suspend fun getWeatherByCity(lat:Double,lon:Double,apiToken: String): Resource<ApiResponse>
    suspend fun getSevenDayWeather(lat:Double,lon:Double,cnt:Int,apiToken: String): Resource<List<ApiResponse>>
    suspend fun getCityLocation(cityName:String,limit:Int, appId:String):Resource<List<ApiLocationResponse>>

}