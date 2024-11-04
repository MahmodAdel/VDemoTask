package com.demo.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.data.model.WeatherDataEntity

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addWeatherItem(post : WeatherDataEntity) : Long



    @Query("SELECT * FROM weather")
    fun getAllWeatherHistory() : List<WeatherDataEntity>

    @Query("SELECT COUNT() FROM weather")
    fun getCount():Int


    @Query("DELETE From weather WHERE id <= (SELECT MIN(m.id) FROM (SELECT id FROM weather ORDER BY id LIMIT 5) m)")
    fun deleteFirstInRaw()
}