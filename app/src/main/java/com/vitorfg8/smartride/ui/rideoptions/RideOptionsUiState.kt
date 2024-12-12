package com.vitorfg8.smartride.ui.rideoptions

import kotlinx.serialization.Serializable


@Serializable
data class RideOptionsUiState(
    val destinationUiState: DestinationUiState = DestinationUiState(),
    val optionUiStates: List<OptionUiState> = listOf(),
    val originUiState: OriginUiState = OriginUiState(),
    val distance: Double = 0.0,
    val duration: String = "",
    val showError: Boolean = false,
    val isLoading: Boolean = false,
    val isConfirmed: Boolean = false,
    val errorMessage: String = ""
)
