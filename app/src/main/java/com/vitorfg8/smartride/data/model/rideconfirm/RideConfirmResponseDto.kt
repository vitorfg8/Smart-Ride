package com.vitorfg8.smartride.data.model.rideconfirm

import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmResponse

data class RideConfirmResponseDto(
    val success: Boolean,
)

fun RideConfirmResponseDto.toRideConfirm(): RideConfirmResponse {
    return RideConfirmResponse(
        success = this.success
    )
}