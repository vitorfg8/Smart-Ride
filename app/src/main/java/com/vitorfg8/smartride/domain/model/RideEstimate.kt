package com.vitorfg8.smartride.domain.model

data class RideEstimate(
    val destination: Destination,
    val distance: Int,
    val duration: String,
    val options: List<Option>,
    val origin: Origin,
    val errorCode: String?,
    val errorDescription: String?,
)
