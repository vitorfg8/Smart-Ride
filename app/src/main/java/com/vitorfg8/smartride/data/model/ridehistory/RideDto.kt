package com.vitorfg8.smartride.data.model.ridehistory

data class RideDto(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: DriverDto,
    val value: Double
)
