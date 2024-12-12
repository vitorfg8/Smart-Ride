package com.vitorfg8.smartride.ui.ridehistory

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideHistoryScreen(
    uiState: RideHistoryUiState,
    onEvent: (RideHistoryEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
        TopAppBar(title = { Text(stringResource(R.string.rides_history)) }, navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        })
    }
    ) { padding ->
        LazyColumn(
            Modifier
                .padding(padding)
                .fillMaxWidth()) {
            item {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(R.string.search_on_history)
                )
                TextField(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    label = { Text(stringResource(R.string.enter_your_id)) },
                    value = uiState.customerId,
                        onValueChange = {
                            onEvent(RideHistoryEvent.UpdateCustomerId(it))
                        })

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    DriverSelector(
                        value = uiState.currentDriverSelected,
                            onValueChange = {
                                onEvent(RideHistoryEvent.SelectDriver(it))
                            }
                    )

                    Button(modifier = Modifier.size(120.dp, 60.dp),
                        onClick = {
                            onEvent(RideHistoryEvent.FilterResults)
                        }) {
                            Text(stringResource(R.string.filter))
                        }
                    }
                    Text(
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        text = stringResource(R.string.last_rides)
                    )

                if (uiState.isLoading) {
                    CircularProgressIndicator()
                }

                LaunchedEffect(key1 = uiState.showError) {
                    snackbarHostState.showSnackbar(uiState.errorDescription)
                }

            }

            items(uiState.rides) {
                HistoryItem(
                    driverName = it.driver.name,
                    date = it.date.toString(),
                    origin = it.origin,
                    destination = it.destination,
                    value = it.value,
                    distance = it.distance,
                    duration = it.duration
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverSelector(
    value: Driver,
    onValueChange: (driverOptions: Driver) -> Unit,
    modifier: Modifier = Modifier,
) {

    val driverOptions = listOf(
        Driver(null, "Todos"),
        Driver(1, "Homer Simpson"),
        Driver(2, "Dominic Toretto"),
        Driver(3, "James Bond")
    )

    val options = driverOptions
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(),
            readOnly = true,
            value = value.name,
            onValueChange = {},
            label = { Text(stringResource(R.string.driver)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption.name) },
                    onClick = {
                        onValueChange(selectionOption)
                        expanded = false
                    })
            }
        }
    }
}

@Preview
@Composable
private fun RideHistoryScreenPreview() {
    SmartRideTheme {
        RideHistoryScreen(
            uiState = RideHistoryUiState(
                isLoading = true,
                customerId = "123",
                rides = listOf(
                    Ride(
                        id = 1,
                        date = "01/12/2024",
                        origin = "São Paulo",
                        destination = "Rio de Janeiro",
                        distance = "100.0 Km",
                        duration = "1h",
                        driver = Driver(1, "Homer Simpson"),
                        value = 10.0
                    ),
                    Ride(
                        id = 1,
                        date = "01/12/2024",
                        origin = "São Paulo",
                        destination = "Rio de Janeiro",
                        distance = "100.0 Km",
                        duration = "1h",
                        driver = Driver(1, "Homer Simpson"),
                        value = 10.0
                    ),
                    Ride(
                        id = 1,
                        date = "01/12/2024",
                        origin = "São Paulo",
                        destination = "Rio de Janeiro",
                        distance = "100.0 Km",
                        duration = "1h",
                        driver = Driver(1, "Homer Simpson"),
                        value = 10.0
                    )
                )
            ),
            onEvent = {})
    }
}