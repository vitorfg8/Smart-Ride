package com.vitorfg8.smartride.domain.model.rideconfirm

import com.google.gson.annotations.SerializedName
import com.vitorfg8.smartride.data.model.rideconfirm.RideConfirmRequestDto

data class RideConfirmRequest(
    @SerializedName("customer_id")
    val customerId: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

fun RideConfirmRequest.toRideConfirmDto(): RideConfirmRequestDto {
    return RideConfirmRequestDto(
        customerId = this.customerId,
        origin = this.origin,
        destination = this.destination,
        distance = this.distance,
        duration = this.duration,
        driverDto = this.driver.toDriverDto(),
        value = this.value
    )
}
