package com.demo.vdemotask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.demo.vdemotask.feature.currentweather.CurrentWeatherScreen
import com.demo.vdemotask.feature.currentweather.currentWeatherScreen
import com.demo.vdemotask.feature.currentweather.navigateToCurrentWeather
import com.demo.vdemotask.feature.daysforecast.daysForeCastScreen
import com.demo.vdemotask.feature.daysforecast.navigateToDaysForeCast
import com.demo.vdemotask.feature.input.presentation.CityInput
import com.demo.vdemotask.feature.input.presentation.cityInputScreen
import kotlin.reflect.KClass

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: KClass<*> = CityInput::class
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        currentWeatherScreen(navigateToDaysForeCast = navController::navigateToDaysForeCast)
        cityInputScreen (navigateToCurrentWeather = navController::navigateToCurrentWeather)
        daysForeCastScreen ()
    }
}
