package com.demo.data.model


data class WeatherDto(
    val id: Int,
    val name: String,
    val condition: String,
    val temperature: Double
)
