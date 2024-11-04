package com.demo.vdemotask.core.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.demo.vdemotask.core.R

@Composable
fun WeatherItem(name:String,temperature:String,condition:String,description:String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), // Add padding to the card
        elevation = CardDefaults.cardElevation(2.dp) // Use CardDefaults.cardElevation instead of elevation
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "City Name: ${name ?: stringResource(R.string.unknown_city)}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, color = Color.Black)
            )
            Text(
                text = "City Temp: ${temperature ?: stringResource(R.string.unknown_city)}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
            )
            Text(
                text = "City Condition: ${condition ?: stringResource(R.string.unknown_city)}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
            )
            Text(
                text = "City Description: ${description ?: stringResource(R.string.unknown_city)}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
            )
        }
    }
}