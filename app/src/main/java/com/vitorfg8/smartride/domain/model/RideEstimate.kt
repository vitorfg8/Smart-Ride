package com.vitorfg8.smartride.domain.model

data class RideEstimate(
    val distance: Int,
    val duration: String,
    val errorCode: String?,
    val errorDescription: String?,
)
