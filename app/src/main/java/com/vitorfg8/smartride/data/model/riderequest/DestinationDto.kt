package com.vitorfg8.smartride.data.model.riderequest


import com.google.gson.annotations.SerializedName

data class DestinationDto(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)