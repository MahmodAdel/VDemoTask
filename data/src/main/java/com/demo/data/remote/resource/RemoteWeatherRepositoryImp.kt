package com.demo.data.remote.resource


import com.demo.data.remote.network.ApiLocationResponse
import com.demo.data.remote.network.ApiResponse
import com.demo.data.remote.network.ApiService
import com.demo.data.repository.BaseRepo
import com.demo.data.repository.RemoteRepository
import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.Resource
import okhttp3.ResponseBody
import javax.inject.Inject

class RemoteWeatherRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : RemoteRepository , BaseRepo() {
    override suspend fun getWeatherByCity(
        lat: Double,
        lon: Double,
        apiToken: String
    ): Resource<ApiResponse> {
        return safeApiCall {apiService.getWeatherByCity(lat, lon, apiToken)
        }
    }


    override suspend fun getSevenDayWeather(
        lat: Double,
        lon: Double,
        crt: Int,
        apiToken: String
    ): Resource<List<ApiResponse>> {
        return  safeApiCall { apiService.getSevenDayWeather(lat, lon, crt, apiToken)}

    }


    override suspend fun getCityLocation(cityName: String, limit: Int, appId: String): Resource<List<ApiLocationResponse>> {
        return safeApiCall {  apiService.getCityLocation(cityName,limit, appId)}

    }


}
