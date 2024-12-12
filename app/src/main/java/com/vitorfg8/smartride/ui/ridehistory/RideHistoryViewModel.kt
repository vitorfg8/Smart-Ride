package com.vitorfg8.smartride.ui.ridehistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorfg8.smartride.domain.model.ridehistory.toUiState
import com.vitorfg8.smartride.domain.repository.RideHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RideHistoryViewModel(
    private val rideHistoryRepository: RideHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RideHistoryUiState())
    val uiState: StateFlow<RideHistoryUiState> = _uiState.asStateFlow()

    fun onEvent(event: RideHistoryEvent) {
        when (event) {
            is RideHistoryEvent.FilterResults -> {
                filterResults()
            }

            is RideHistoryEvent.SelectDriver -> {
                selectDriver(event)
            }

            is RideHistoryEvent.UpdateCustomerId -> {
                updateCustomerId(event)
            }
        }
    }

    private fun filterResults() {
        viewModelScope.launch {
            rideHistoryRepository.getRideHistory(
                uiState.value.customerId,
                uiState.value.currentDriverSelected.id
            ).onStart {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        showError = false,
                        rides = listOf()
                    )
                }
            }.catch { error ->
                var message = ""
                if (error is HttpException) {
                    when (error.code()) {
                        400 -> message = "Motorista invalido. Tente novamente"
                        404 -> message = "Nenhum registro encontrado. Tente novamente"
                        else -> message = "Erro desconhecido. Tente novamente"
                    }
                }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        showError = true,
                        errorMessage = message,
                        rides = listOf()
                    )
                }
            }.collect { result ->
                _uiState.update {
                    it.copy(
                        rides = result.rides.map { it.toUiState() },
                        showError = false,
                        isLoading = false
                    )
                }
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