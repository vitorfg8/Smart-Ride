package com.vitorfg8.smartride.data.model.rideconfirm

import com.google.gson.annotations.SerializedName

data class RideConfirmRequestDto(
    @SerializedName("customer_id")
    val customerId: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driverDto: DriverDto,
    val value: Double
)
