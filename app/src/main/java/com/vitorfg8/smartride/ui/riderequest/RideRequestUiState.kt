package com.vitorfg8.smartride.ui.riderequest

import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState

data class RideRequestUiState(
    val customerId: String = "",
    val origin: String = "",
    val destination: String = "",
    val isEstimateSuccessful: Boolean? = null,
    val rideOptions: RideOptionsUiState? = null
)
