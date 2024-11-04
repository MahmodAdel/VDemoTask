package com.demo.vdemotask.feature.input.resources

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.demo.vdemotask.core.R

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(id = R.string.search),
    placeholder: String = stringResource(id = R.string.search),
) {


    val trailingIcon = @Composable {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        }
    }

    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold
    )
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        placeholder = { Text(placeholder) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray, // Custom focused border color
            unfocusedBorderColor = Color.LightGray // Custom unfocused border color
        ),
        textStyle = TextStyle(textDirection = TextDirection.ContentOrLtr),
        shape = RoundedCornerShape(10.dp)


    )
}*/
