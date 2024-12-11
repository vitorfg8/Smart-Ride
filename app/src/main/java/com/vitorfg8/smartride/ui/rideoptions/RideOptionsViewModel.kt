package com.vitorfg8.smartride.ui.rideoptions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmRequest
import com.vitorfg8.smartride.domain.repository.RideConfirmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RideOptionsViewModel(private val rideConfirmRepository: RideConfirmRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RideOptionsUiState())
    val uiState: StateFlow<RideOptionsUiState> = _uiState.asStateFlow()

    fun onEvent(event: RideOptionsEvent) {
        when (event) {
            is RideOptionsEvent.UpdateState -> {
                _uiState.update {
                    it.copy(
                        destinationUiState = event.rideOptionsUiState.destinationUiState,
                        optionUiStates = event.rideOptionsUiState.optionUiStates,
                        originUiState = event.rideOptionsUiState.originUiState,
                        distance = event.rideOptionsUiState.distance,
                        duration = event.rideOptionsUiState.duration
                    )
                }
            }

            is RideOptionsEvent.ConfirmRide -> {
                confirmRide(event.rideConfirmRequest)
            }

            else -> Unit
        }
    }

    private fun confirmRide(rideConfirmRequest: RideConfirmRequest) {
        Log.d("TESTE", "rideConfirmRequest: $rideConfirmRequest")
        viewModelScope.launch {
            rideConfirmRepository.confirmRide(
                rideConfirmRequest //TODO refactor
            ).catch {
                _uiState.update {
                    it.copy(
                        showError = true,
                        errorMessage = it.errorMessage
                    )
                }
            }.collect {
                Log.d("TESTE", "confirmRide: $it") //TODO
            }
        }
    }
}