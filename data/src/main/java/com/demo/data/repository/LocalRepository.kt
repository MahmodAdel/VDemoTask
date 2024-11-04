package com.demo.data.repository

import androidx.room.Query
import com.demo.data.model.WeatherDataEntity

interface LocalRepository {

    suspend fun addWeatherItem(post: WeatherDataEntity): Long
    suspend fun getAllWeatherHistory(): List<WeatherDataEntity>
    suspend fun getCount():Int
    suspend fun deleteFirstInRaw()
}