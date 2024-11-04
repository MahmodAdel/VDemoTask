package com.demo.vdemotask.feature.input.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.domain.model.Location
import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.Resource
import com.demo.vdemotask.core.common.SearchView
import com.demo.vdemotask.core.common.TopBar
import com.demo.vdemotask.core.common.WeatherItem
import com.demo.vdemotask.feature.cityinput.R
import kotlinx.serialization.Serializable

const val TAG = "CityInputScreen"
@Composable
fun CityInputRoute(navigateToCurrentWeather: (location: Location) -> Unit) {
    CityInputScreen() {
        navigateToCurrentWeather(it)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CityInputScreen(
    viewModel: CityInputViewModel = hiltViewModel(), onClick: (Location) -> Unit
) {

    val context = LocalContext.current
    val historyList by viewModel.weatherList.collectAsState()

    var credentials by remember { mutableStateOf(Credentials()) }
    val searchHistory by viewModel.searchHistory.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val scrollState = rememberScrollState()
    when (searchHistory) {
        is Resource.Error -> {
            Toast.makeText(context, (searchHistory as Resource.Error).exception.message,Toast.LENGTH_LONG).show()
        }

        is Resource.Loading -> {
            Log.d(TAG, "LoginScreen: loading...")
        }

        is Resource.Success -> {

            onClick(Location((searchHistory as Resource.Success<Weather>).data.lat.toString(),(searchHistory as Resource.Success<Weather>).data.lon.toString()))
            viewModel._searchHistory.value = Resource.InitScreen()


        }

        is Resource.InitScreen -> {
            Log.d(TAG, "LoginScreen: InitScreen...")

        }

        else -> {}
    }
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp) // Adjust the size as needed
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 60.dp),
                    verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_android_black_24dp), contentDescription = "",
                contentScale = ContentScale.Fit

            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchView.SearchField(
                value = credentials.search,
                onChange = { data -> credentials = credentials.copy(search = data) },
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.please_input_city),
                placeholder = stringResource(id = R.string.please_input_city),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (!checkCredentials(credentials, viewModel)) credentials =
                        Credentials()
                },
                enabled = credentials.isNotEmpty(),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Red
                )
            ) {
                Text(
                    stringResource(R.string.confirm),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(historyList) { weather ->
                    WeatherItem(weather.name,weather.temperature.toString(),weather.condition,weather.description)
                }
            }
        }


    }
}

fun checkCredentials(credentials: Credentials, viewModel: CityInputViewModel): Boolean {
    viewModel.loadWeatherHistory(credentials.search)
    return true
}

data class Credentials(
    var search: String = "",
    var remember: Boolean = false,
) {
    fun isNotEmpty(): Boolean {
        return search.isNotEmpty() && search.length > 3
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    CityInputScreen {}
}