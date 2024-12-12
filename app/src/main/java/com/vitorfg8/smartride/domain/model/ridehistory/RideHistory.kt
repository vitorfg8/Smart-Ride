package com.vitorfg8.smartride.domain.model.ridehistory

data class RideHistory(
    val customerId: String,
    val rides: List<Ride>
)