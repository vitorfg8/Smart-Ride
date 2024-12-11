package com.vitorfg8.smartride.domain.model.rideconfirm

import com.vitorfg8.smartride.data.model.rideconfirm.DriverDto

data class Driver(
    val id: Int,
    val name: String
)

fun Driver.toDriverDto(): DriverDto {
    return DriverDto(
        id = this.id,
        name = this.name
    )
}