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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
                rideConfirmRequest
            ).onStart {
                _uiState.update {
                    it.copy(
                        showError = false, isLoading = true, isConfirmed = false
                    )
                }
            }.catch { error ->
                var message = ""
                if (error is HttpException) {
                    when (error.code()) {
                        400 -> message =
                            "Os dados fornecidos no corpoda requisição são inválidos. Tente novamente"
                        404 -> message = "Motorista não encontrado. Tente novamente"
                        406 -> message = "Quilometragem inválida para o motorista. Tente novamente"
                        else -> message = "Erro desconhecido. Tente novamente"
                    }
                }
                _uiState.update {
                    it.copy(
                        showError = true,
                        isLoading = false,
                        isConfirmed = false,
                        errorMessage = message
                    )
                }
            }.collect {
                /*if (rideConfirmRequest.driver.id == 1 && rideConfirmRequest.distance.toKm() >= 1 ||
                    rideConfirmRequest.driver.id == 2 && rideConfirmRequest.distance.toKm() >= 5 ||
                    rideConfirmRequest.driver.id == 3 && rideConfirmRequest.distance.toKm() >= 10) {*/

                _uiState.update {
                    it.copy(
                        isLoading = false, showError = false, isConfirmed = true
                    )
                }
                /*} else {
                    _uiState.update {
                        it.copy(
                            showError = true,
                            isLoading = false,
                            isConfirmed = false,
                            errorMessage = "Quilometragem inválida para o motorista"
                        )
                    }
                }*/
            }

        }
    }

    private fun Double.toKm(): Double {
        return this / 1000
    }
}