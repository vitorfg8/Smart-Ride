package com.vitorfg8.smartride.domain.model.rideconfirm

data class RideConfirmResponse(
    val success: Boolean,
    val errorCode: String,
    val errorDescription: String
)