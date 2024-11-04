package com.demo.data.remote.network

import com.demo.domain.model.Weather
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getWeatherByCity(@Query("lat") lat: Double,@Query("lon") lon: Double,@Query("appid") appid: String): Response<ApiResponse>
    @GET("geo/1.0/direct")
    suspend fun getCityLocation(@Query("q") cityName:String,@Query("limit") limit:Int,@Query("appId") appId:String):Response<List<ApiLocationResponse>>
    @GET("data/2.5/forecast/daily")
    suspend fun getSevenDayWeather(@Query("lat") lat: Double,@Query("lon") lon: Double,@Query("cnt") cnt: Int,@Query("appid") appid: String): Response<List<ApiResponse>>
}