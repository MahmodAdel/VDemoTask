package com.demo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationForeCast(var lat:String, var lon:String)