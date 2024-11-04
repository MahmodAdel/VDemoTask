package com.demo.data.remote.network

import com.google.gson.annotations.SerializedName

data class ApiLocationResponse(
    val name: String,
    @SerializedName("local_names")
    val localNames: LocalNames?,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String,
)

data class LocalNames(
    val en: String, )
