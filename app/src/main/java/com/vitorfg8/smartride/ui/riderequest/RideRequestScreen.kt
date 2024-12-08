package com.vitorfg8.smartride.ui.riderequest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@Composable
fun RideRequestScreen(
    uiState: RideRequestUiState,
    onEvent: (RideRequestEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 8.dp),
                label = { Text(stringResource(R.string.enter_your_id)) },
                value = uiState.customerId,
                onValueChange = { newValue ->
                    onEvent(RideRequestEvent.UpdateCustomerId(newValue))
                })
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
                label = { Text(stringResource(R.string.enter_your_starting_location)) },
                value = uiState.origin,
                onValueChange = { newValue ->
                    onEvent(RideRequestEvent.UpdateOrigin(newValue))
                })
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp),
                label = { Text(stringResource(R.string.enter_your_arrival_location)) },
                value = uiState.destination,
                onValueChange = { newValue ->
                    onEvent(RideRequestEvent.UpdateDestination(newValue))
                })
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), onClick = {
                onEvent(RideRequestEvent.EstimateRide)
            }) {
                Text(stringResource(R.string.estimate_the_value_of_the_trip))
            }
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                strokeWidth = 4.dp
            )
        }
    }
}

@Preview
@Composable
private fun RequestRideScreenPreview() {
    SmartRideTheme {
        RideRequestScreen(
            uiState = RideRequestUiState(
                customerId = "123",
                origin = "Rua A",
                destination = "Rua B"
            ),
            onEvent = {}
        )
    }
}