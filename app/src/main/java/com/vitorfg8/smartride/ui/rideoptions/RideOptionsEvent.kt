package com.vitorfg8.smartride.ui.rideoptions

import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmRequest

sealed class RideOptionsEvent {
    data class UpdateState(val rideOptionsUiState: RideOptionsUiState) : RideOptionsEvent()
    data class ConfirmRide(val rideConfirmRequest: RideConfirmRequest) : RideOptionsEvent()
    object GoBack : RideOptionsEvent()
    object NavigateToHistory : RideOptionsEvent()
}