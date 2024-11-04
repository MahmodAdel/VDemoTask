package com.demo.data.local.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.data.model.WeatherDataEntity

@Database(entities = [WeatherDataEntity::class], version = 1)
abstract class AppDataBase :RoomDatabase(){

    abstract fun serviceDao(): ServiceDao


}