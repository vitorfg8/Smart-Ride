package com.vitorfg8.smartride.data.model.ridehistory

import com.vitorfg8.smartride.domain.model.ridehistory.Driver
import com.vitorfg8.smartride.domain.model.ridehistory.Ride
import com.vitorfg8.smartride.domain.model.ridehistory.RideHistory

data class RideHistoryDto(
    val customer_id: String,
    val rides: List<RideDto>,
    val error_code: String,
    val error_description: String
)

fun RideHistoryDto.toRideHistory(): RideHistory {
    return RideHistory(
        customerId = this.customer_id,
        rides = this.rides.map { it.toRide() }
    )
}

fun RideDto.toRide(): Ride {
    return Ride(
        id = this.id,
        date = this.date,
        origin = this.origin,
        destination = this.destination,
        distance = this.distance,
        duration = this.duration,
        driver = this.driver.toDriver(),
        value = this.value
    )
}

fun DriverDto.toDriver(): Driver {
    return Driver(
        id = this.id,
        name = this.name
    )
}
