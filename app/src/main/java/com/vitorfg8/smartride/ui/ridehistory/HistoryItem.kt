package com.vitorfg8.smartride.ui.ridehistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@Composable
fun HistoryItem(
    driverName: String,
    date: String,
    origin: String,
    destination: String,
    value: Double,
    distance: String,
    duration: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "$driverName • $distance km",
                        /* style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)*/
                    )
                    Text(
                        "R$ $value",
                        /*style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)*/
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = date, style = MaterialTheme.typography.bodySmall)
                        Text(text = duration, style = MaterialTheme.typography.bodySmall)
                    }
                    Row(modifier = Modifier.padding(vertical = 4.dp)) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.pin_heroicons),
                            contentDescription = null,
                            tint = Color(0xFF008000)
                        )
                        Text(text = origin)
                    }
                    Row(modifier = Modifier.padding(vertical = 4.dp)) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.pin_heroicons),
                            contentDescription = null,
                            tint = Color(0xFFFF5349)
                        )
                        Text(text = destination)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun OptionsItemPreview() {
    SmartRideTheme {
        HistoryItem(
            driverName = "Tony Stark",
            date = "01/12/2024 às 20:20",
            origin = "São Paulo",
            destination = "Rio de Janeiro",
            value = 100.0,
            distance = "10.0 Km",
            duration = "10 min"
        )
    }
}