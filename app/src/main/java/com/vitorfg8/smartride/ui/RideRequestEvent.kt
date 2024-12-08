package com.vitorfg8.smartride.ui

sealed class RideRequestEvent {
    data class UpdateCustomerId(val customerId: String) : RideRequestEvent()
    data class UpdateOrigin(val origin: String) : RideRequestEvent()
    data class UpdateDestination(val destination: String) : RideRequestEvent()
    object EstimateRide : RideRequestEvent()
}