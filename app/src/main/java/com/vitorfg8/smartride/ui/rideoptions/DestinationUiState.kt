package com.vitorfg8.smartride.ui.rideoptions

import kotlinx.serialization.Serializable


@Serializable
data class DestinationUiState(
    val latitude: Double,
    val longitude: Double
)