package com.vitorfg8.smartride.ui.ridehistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideHistoryScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier, topBar = {
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
        LazyColumn(Modifier.padding(padding)) {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(R.string.search_on_history)
                )
                Column {
                    TextField(label = { Text("Digite o id do usuário") },
                        value = "",
                        onValueChange = {

                        })

                    Row {
                        TextField(label = { Text("Digite o id do usuário") },
                            value = "",
                            onValueChange = {

                            })

                        Button(onClick = {}) {
                            Text(stringResource(R.string.filter))
                        }
                    }
                    Text(
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        text = stringResource(R.string.last_rides)
                    )
                }
            }
            items(3) {
                HistoryItem(
                    driverName = "Tony Stark",
                    date = "01/12/2024 às 20:20",
                    origin = "São Paulo",
                    destiny = "Rio de Janeiro",
                    value = 100.0,
                    distance = 10.0,
                    duration = "10 min"
                )
            }
        }
    }
}

@Preview
@Composable
private fun RideHistoryScreenPreview() {
    SmartRideTheme {
        RideHistoryScreen()
    }
}