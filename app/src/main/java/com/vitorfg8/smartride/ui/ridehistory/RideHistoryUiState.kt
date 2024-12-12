package com.vitorfg8.smartride.ui.ridehistory


import java.util.Date

data class RideHistoryUiState(
    val customerId: String = "",
    val rides: List<Ride> = listOf(),
    val errorCode: String = "",
    val errorDescription: String = "",
    val isLoading: Boolean = false,
    val currentDriverSelected: Driver = Driver(0, "Todos")
)

data class Ride(
    val id: Int,
    val date: Date,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

data class Driver(
    val id: Int,
    val name: String
)
