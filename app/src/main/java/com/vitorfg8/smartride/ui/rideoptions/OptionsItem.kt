package com.vitorfg8.smartride.ui.rideoptions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@Composable
fun OptionsItem(
    driverName: String,
    value: Double,
    vehicle: String,
    description: String,
    rating: Int,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Image(
                modifier = Modifier
                    .size(72.dp)
                    .padding(end = 8.dp),
                painter = painterResource(R.drawable.car_front_vehicle_icon),
                contentDescription = null
            )

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        driverName,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Text(
                        "R$ $value",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(vehicle, maxLines = 1)
                    Text(" • $rating ")
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.star_heroicons),
                        contentDescription = null
                    )
                }
                Text(text = description, style = MaterialTheme.typography.labelSmall)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Button(onClick = { onSelect() }) {
                        Text(stringResource(R.string.select))
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
        OptionsItem(
            driverName = "Tony Stark",
            value = 100.0,
            vehicle = "Audi R8 V10 coupe",
            description = "The truth is… I am Iron Man.",
            rating = 5,
            onSelect = {}
        )
    }
}