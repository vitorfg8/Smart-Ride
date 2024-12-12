package com.vitorfg8.smartride.ui.riderequest

import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState

data class RideRequestUiState(
    val customerId: String = "",
    val origin: String = "",
    val destination: String = "",
    val distance: Double = 0.0,
    val duration: String = "",
    val isEstimateSuccessful: Boolean? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val rideOptions: RideOptionsUiState? = null
)
