package com.vitorfg8.smartride.ui.ridehistory


data class RideHistoryUiState(
    val customerId: String = "",
    val rides: List<Ride> = listOf(),
    val errorDescription: String = "",
    val isLoading: Boolean = false,
    val showError: Boolean = false,
    val currentDriverSelected: Driver = Driver(0, "Todos")
)

data class Ride(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val distance: String,
    val duration: String,
    val driver: Driver,
    val value: Double
)

data class Driver(
    val id: Int?,
    val name: String
)
