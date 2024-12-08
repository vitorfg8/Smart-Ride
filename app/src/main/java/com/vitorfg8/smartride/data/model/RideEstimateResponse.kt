package com.vitorfg8.smartride.data.model


import com.google.gson.annotations.SerializedName
import com.vitorfg8.smartride.domain.model.RideEstimate

data class RideEstimateResponse(
    @SerializedName("destination")
    val destination: Destination,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("routeResponse")
    val routeResponse: RouteResponse,
    @SerializedName("error_code")
    val errorCode: String?,
    @SerializedName("error_description")
    val errorDescription: String?,
)

fun RideEstimateResponse.toRideEstimate() = RideEstimate(
    distance = distance,
    duration = duration,
    errorCode = errorCode,
    errorDescription = errorDescription
)