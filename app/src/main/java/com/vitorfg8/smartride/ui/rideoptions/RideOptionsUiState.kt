package com.vitorfg8.smartride.ui.rideoptions

import kotlinx.serialization.Serializable


@Serializable
data class RideOptionsUiState(
    val destinationUiState: DestinationUiState,
    val optionUiStates: List<OptionUiState>,
    val originUiState: OriginUiState,
)
