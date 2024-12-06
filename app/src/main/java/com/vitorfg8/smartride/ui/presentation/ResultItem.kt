package com.vitorfg8.smartride.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitorfg8.smartride.R
import com.vitorfg8.smartride.ui.theme.SmartRideTheme

@Composable
fun ResultItem(modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
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
                        "Fulano D. Tal",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Text("R$ 50", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Ford Fiesta Creme • 5")
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.star_heroicons),
                        contentDescription = null
                    )
                }
                Text("Lorem Ipsum", style = MaterialTheme.typography.labelSmall)

            }
        }
    }
}

@Preview
@Composable
private fun ResultItemPreview() {
    SmartRideTheme {
        ResultItem()
    }
}