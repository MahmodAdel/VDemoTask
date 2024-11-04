package com.demo.vdemotask.feature.input.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.demo.domain.model.Location
import kotlinx.serialization.Serializable

@Serializable
object CityInput

fun NavController.navigateToCityInput(navOptions: NavOptions? = null) {
    navigate(CityInput, navOptions)
}

fun NavGraphBuilder.cityInputScreen(navigateToCurrentWeather: (Location) -> Unit) {
    composable<CityInput> {
        CityInputRoute(navigateToCurrentWeather)
    }
}
