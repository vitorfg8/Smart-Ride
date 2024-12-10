package com.vitorfg8.smartride.ui.rideoptions

import kotlinx.serialization.Serializable


@Serializable
data class OptionUiState(
    val description: String,
    val id: Int,
    val name: String,
    val rating: Int,
    val value: Double,
    val vehicle: String
)