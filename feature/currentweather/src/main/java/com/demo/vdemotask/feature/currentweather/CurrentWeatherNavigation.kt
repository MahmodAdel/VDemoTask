package com.demo.vdemotask.feature.currentweather

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.demo.domain.model.Location
import com.demo.domain.model.LocationForeCast
import kotlinx.serialization.Serializable



fun NavController.navigateToCurrentWeather(location: Location, navOptions: NavOptions? = null) {
    navigate(location, navOptions)
}

fun NavGraphBuilder.currentWeatherScreen(navigateToDaysForeCast: (LocationForeCast) -> Unit) {
    composable<Location> {
        val lat = it.toRoute<Location>().lat  // Type safe access
        val lon = it.toRoute<Location>().lon  // Type safe access
        CurrentWeatherRoute(lat,lon,navigateToDaysForeCast)
    }
}
