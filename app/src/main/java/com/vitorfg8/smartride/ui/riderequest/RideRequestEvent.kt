package com.vitorfg8.smartride.ui.riderequest

import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState

sealed class RideRequestEvent {
    data class UpdateCustomerId(val customerId: String) : RideRequestEvent()
    data class UpdateOrigin(val origin: String) : RideRequestEvent()
    data class UpdateDestination(val destination: String) : RideRequestEvent()
    object EstimateRide : RideRequestEvent()
    data class NavigateToRideOptions(
        val rideOptions: RideOptionsUiState,
        val customerId: String,
        val origin: String,
        val destination: String,
    ) : RideRequestEvent()
}