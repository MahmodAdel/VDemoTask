package com.demo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(var lat:String,var lon:String)