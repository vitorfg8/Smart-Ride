package com.vitorfg8.smartride.ui.rideoptions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideOptionsScreen(
    uiState: RideOptionsUiState,
    onEvent: (RideOptionsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = { Text(stringResource(R.string.ride_options)) }, navigationIcon = {
            IconButton(onClick = {
                onEvent(RideOptionsEvent.GoBack)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        })
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.estimated_route),
                    style = MaterialTheme.typography.titleMedium,
                )
                Map()
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(R.string.driver_options)
                )
            }
            items(uiState.optionUiStates) {
                OptionsItem(
                    driverName = it.name,
                    value = it.value,
                    vehicle = it.vehicle,
                    description = it.description,
                    rating = it.rating,
                    onSelect = { onEvent(RideOptionsEvent.NavigateToHistory) }
                )
            }
        }
    }
}

@Composable
private fun Map(modifier: Modifier = Modifier) {

    val cameraPosition = LatLng(-23.5215624, -46.763286699999995)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cameraPosition, 5f)
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    val routeCoordinates = listOf(
        LatLng(-23.5215624, -46.763286699999995),
        LatLng(-23.5615351, -46.6562816),
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(16.dp)
    ) {

        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            Polyline(
                points = routeCoordinates, clickable = true, color = Color.Blue, width = 5f
            )
        }
    }

}

@Preview
@Composable
private fun RideOptionsScreenPreview() {
    SmartRideTheme {
        RideOptionsScreen(uiState = RideOptionsUiState(
            destinationUiState = DestinationUiState(
                latitude = -23.5615351, longitude = -46.6562816
            ),
            optionUiStates = listOf(
                OptionUiState(
                    description = "The truth is… I am Iron Man.",
                    id = 1,
                    rating = 3,
                    value = 100.0,
                    name = "Tony Stark",
                    vehicle = "Audi R8 V10 coupe"
                ), OptionUiState(
                    description = "I was meant to rule everything.",
                    id = 1,
                    rating = 4,
                    value = 60.0,
                    name = "Wanda Maximoff",
                    vehicle = "Buick Verano"
                ), OptionUiState(
                    description = "I'm not deaf, just hard of hearing.",
                    id = 1,
                    rating = 5,
                    value = 70.0,
                    name = "Clint Barton",
                    vehicle = "Dodge Challenger 1970"
                )
            ),
            originUiState = OriginUiState(
                latitude = -23.5215624, longitude = 46.763286699999995
            ),
        ), onEvent = {})
    }
}