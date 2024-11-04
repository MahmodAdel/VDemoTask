package com.demo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class WeatherDataEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @SerializedName("city_name")
    val name: String,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("timeStamp")
    val timeStamp:String,
    @SerializedName("lat")
    var lat:Double,
    @SerializedName("lon")
    val lon:Double
)