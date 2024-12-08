package com.vitorfg8.smartride.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RideRequestViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(RideRequestUiState())
    val uiState: StateFlow<RideRequestUiState> = _uiState.asStateFlow()

    fun onEvent(event: RideRequestEvent) {
        when (event) {
            is RideRequestEvent.UpdateCustomerId -> updateCustomerId(event.customerId)
            is RideRequestEvent.UpdateOrigin -> updateOrigin(event.origin)
            is RideRequestEvent.UpdateDestination -> updateDestination(event.destination)
            is RideRequestEvent.EstimateRide -> {
                //estimateRide(event.customerId, event.origin, event.destination)
            }
        }
    }

    private fun updateCustomerId(customerId: String) {
        _uiState.update {
            it.copy(customerId = customerId)
        }
    }

    private fun updateOrigin(origin: String) {
        _uiState.update {
            it.copy(origin = origin)
        }
    }

    private fun updateDestination(destination: String) {
        _uiState.update {
            it.copy(destination = destination)
        }
    }
}