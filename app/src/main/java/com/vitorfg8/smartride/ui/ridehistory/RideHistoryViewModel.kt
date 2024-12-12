package com.vitorfg8.smartride.ui.ridehistory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RideHistoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RideHistoryUiState())
    val uiState: StateFlow<RideHistoryUiState> = _uiState.asStateFlow()

    fun onEvent(event: RideHistoryEvent) {
        when (event) {
            is RideHistoryEvent.FilterResults -> {

            }

            is RideHistoryEvent.SelectDriver -> {
                selectDriver(event)
            }

            is RideHistoryEvent.UpdateCustomerId -> {
                updateCustomerId(event)
            }
        }
    }

    private fun selectDriver(driver: RideHistoryEvent.SelectDriver) {
        _uiState.update {
            it.copy(
                currentDriverSelected = driver.driver
            )
        }
    }

    private fun updateCustomerId(event: RideHistoryEvent.UpdateCustomerId) {
        _uiState.update {
            it.copy(
                customerId = event.customerId
            )
        }
    }
}