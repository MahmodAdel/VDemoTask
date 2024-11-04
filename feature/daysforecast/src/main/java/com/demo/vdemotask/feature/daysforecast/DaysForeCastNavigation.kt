package com.demo.vdemotask.feature.daysforecast

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.demo.domain.model.Location
import com.demo.domain.model.LocationForeCast
import kotlinx.serialization.Serializable


fun NavController.navigateToDaysForeCast(location: LocationForeCast,navOptions: NavOptions? = null) {
    navigate(location, navOptions)
}

fun NavGraphBuilder.daysForeCastScreen() {
    composable<LocationForeCast> {
        val lat = it.toRoute<LocationForeCast>().lat  // Type safe access
        val lon = it.toRoute<LocationForeCast>().lon  // Type safe access
        DaysForeCastRoute(lat,lon)
    }
}
