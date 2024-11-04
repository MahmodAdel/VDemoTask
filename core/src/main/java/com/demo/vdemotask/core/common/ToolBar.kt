package com.demo.vdemotask.core.common

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String,onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onClick()
            }) {
                Icon(Icons.Filled.List, "backIcon", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
    )
}