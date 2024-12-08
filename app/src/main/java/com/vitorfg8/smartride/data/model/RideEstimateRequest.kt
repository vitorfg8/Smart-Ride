package com.vitorfg8.smartride.data.model

data class RideEstimateRequest(
    val customer_id: String,
    val origin: String,
    val destination: String
)