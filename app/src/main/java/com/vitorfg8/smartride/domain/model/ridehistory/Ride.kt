package com.vitorfg8.smartride.domain.model.ridehistory

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Ride(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

fun Ride.toUiState(): com.vitorfg8.smartride.ui.ridehistory.Ride {
    return com.vitorfg8.smartride.ui.ridehistory.Ride(
        id = this.id,
        date = this.date.toFormattedDateTime(),
        origin = this.origin,
        destination = this.destination,
        distance = "%.2f".format(this.distance),
        duration = this.duration,
        driver = com.vitorfg8.smartride.ui.ridehistory.Driver(
            id = this.driver.id,
            name = this.driver.name
        ),
        value = "%.2f".format(this.value).toDouble()
    )
}

private fun String.toFormattedDateTime(): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val dateTime = LocalDateTime.parse(this, formatter)
        dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm"))
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}