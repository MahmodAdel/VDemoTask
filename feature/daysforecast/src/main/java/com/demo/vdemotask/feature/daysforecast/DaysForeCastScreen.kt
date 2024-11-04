package com.demo.vdemotask.feature.daysforecast

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.WeatherItem
import com.demo.vdemotask.feature.daysforecast.contract.DaysForeCastContract
import kotlinx.coroutines.launch

@Composable
fun DaysForeCastRoute(lat: String, lon: String) {
    DaysForeCastScreen(lat, lon)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DaysForeCastScreen(
    lat: String,
    lon: String,
    viewModel: DaysForeCastViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val weatherList = remember { mutableStateOf<List<Weather>?>(null) }
    val errorMsg = remember { mutableStateOf<String>("") }
    val loading = remember { mutableStateOf<Boolean>(false) }

    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.initData(lat, lon)

    }
    coroutineScope.launch {
        viewModel.uiState.collect {
            when (val state = it.fetchDataState) {
                DaysForeCastContract.FetchDataState.Finishing -> TODO()
                DaysForeCastContract.FetchDataState.Idle -> {
                    loading.value = false
                    errorMsg.value = ""
                }

                DaysForeCastContract.FetchDataState.Loading -> {
                    loading.value = true
                }

                is DaysForeCastContract.FetchDataState.Success -> {
                    loading.value = false
                    errorMsg.value = ""
                    weatherList.value = state.weather
                }
            }
        }

    }
    coroutineScope.launch {
        viewModel.effect.collect {
            val msg = it.toString()
            errorMsg.value = msg
        }
    }
    if (errorMsg.value != "") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = errorMsg.value,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
            )
        }
    }
    if (loading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp) // Adjust the size as needed
            )
        }
    }
    if (weatherList.value != null && weatherList.value?.isNotEmpty()!!) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 60.dp)
        ) {
            LazyColumn {
                items(weatherList.value!!) { weather ->
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

@Composable
fun ShowList(historyList: List<Weather>) {

}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    DaysForeCastScreen("", "")
}