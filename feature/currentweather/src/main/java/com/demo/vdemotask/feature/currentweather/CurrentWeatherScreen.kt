package com.demo.vdemotask.feature.currentweather

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.domain.model.Location
import com.demo.domain.model.LocationForeCast
import com.demo.vdemotask.core.common.TopBar
import com.demo.vdemotask.core.common.WeatherItem

@Composable
fun CurrentWeatherRoute(lat: String, lon: String, navigateToDaysForeCast: (location:LocationForeCast) -> Unit) {


    CurrentWeatherScreen(lat, lon) {
        navigateToDaysForeCast(it)
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CurrentWeatherScreen(
    lat: String,
    lon: String,
    viewModel: HistoryWeatherViewModel = hiltViewModel(),
    onClick: (location:LocationForeCast) -> Unit
) {
    val historyList by viewModel.weatherList.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()
    Scaffold(topBar = {
        TopBar(title = "Check ForeCast For City") {
            onClick(LocationForeCast(lat,lon))
        }
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 60.dp)
        ) {
            if (isLoading && historyList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp) // Adjust the size as needed
                    )
                }
            } else {
                Column {
                    LazyColumn {
                        items(historyList) { weather ->
                            WeatherItem(
                                weather.name,
                                weather.temperature.toString(),
                                weather.condition,
                                weather.description
                            )
                        }
                    }
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    CurrentWeatherScreen("0.0", "0.0") {}
}