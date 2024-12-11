package com.vitorfg8.smartride.ui.rideoptions

import kotlinx.serialization.Serializable

@Serializable
data class OriginUiState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)