package com.vitorfg8.smartride.ui.ridehistory

sealed class RideHistoryEvent {
    object FilterResults : RideHistoryEvent()
    data class UpdateCustomerId(val customerId: String) : RideHistoryEvent()
    data class SelectDriver(val driver: Driver) : RideHistoryEvent()
}