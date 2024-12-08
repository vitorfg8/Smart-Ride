package com.vitorfg8.smartride.data.model


import com.google.gson.annotations.SerializedName

data class DestinationResponse(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)