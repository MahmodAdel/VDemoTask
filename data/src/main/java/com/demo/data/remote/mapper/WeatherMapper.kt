package com.demo.data.remote.mapper


import com.demo.data.model.WeatherDataEntity
import com.demo.data.remote.network.ApiResponse
import com.demo.domain.model.Weather
import com.demo.vdemotask.core.utils.Utilities


fun WeatherDataEntity.toDomainFromLocal(): Weather {
    return Weather(
        name, temperature, condition, description, timeStamp,lat,lon
    )
}

fun ApiResponse.toDomainFromLocal(): Weather {
    return Weather(
        name = name,main.temp,weather[0].main, weather[0].description,Utilities.getDateTime(),coord.lat,coord.lon
    )
}

fun ApiResponse.toLocalFromRemote(): WeatherDataEntity {
    return WeatherDataEntity(
        name=name, temperature = main.temp, condition = weather[0].main, description = weather[0].description
                ,timeStamp = Utilities.getDateTime(), lat = coord.lat, lon = coord.lon
    )
}
