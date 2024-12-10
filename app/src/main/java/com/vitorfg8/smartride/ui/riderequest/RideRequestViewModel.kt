package com.vitorfg8.smartride.ui.riderequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorfg8.smartride.domain.model.riderequest.toRideOptionsUiState
import com.vitorfg8.smartride.domain.repository.RideEstimateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RideRequestViewModel(
    private val rideEstimateRepository: RideEstimateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RideRequestUiState())
    val uiState: StateFlow<RideRequestUiState> = _uiState.asStateFlow()

    fun onEvent(event: RideRequestEvent) {
        when (event) {
            is RideRequestEvent.UpdateCustomerId -> updateCustomerId(event.customerId)
            is RideRequestEvent.UpdateOrigin -> updateOrigin(event.origin)
            is RideRequestEvent.UpdateDestination -> updateDestination(event.destination)
            is RideRequestEvent.EstimateRide -> {
                estimateRide()
            }
            else -> Unit
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

    private fun estimateRide() {
        viewModelScope.launch {
            rideEstimateRepository.estimateRide(
                _uiState.value.customerId,
                _uiState.value.origin,
                _uiState.value.destination
            ).catch {
                _uiState.update {
                    it.copy(isEstimateSuccessful = false)
                }
            }.collect { rideEstimate ->
                _uiState.update {
                    it.copy(
                        isEstimateSuccessful = true,
                        rideOptions = rideEstimate.toRideOptionsUiState()
                    )
                }
            }
        }
    }
}