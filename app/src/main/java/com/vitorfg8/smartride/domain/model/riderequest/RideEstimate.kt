package com.vitorfg8.smartride.domain.model.riderequest

import com.vitorfg8.smartride.ui.rideoptions.DestinationUiState
import com.vitorfg8.smartride.ui.rideoptions.OptionUiState
import com.vitorfg8.smartride.ui.rideoptions.OriginUiState
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState

data class RideEstimate(
    val destination: Destination,
    val distance: Double,
    val duration: String,
    val options: List<Option>,
    val origin: Origin,
    val errorCode: String?,
    val errorDescription: String?,
)

fun RideEstimate.toRideOptionsUiState(): RideOptionsUiState {
    return RideOptionsUiState(
        destinationUiState = this.destination.toDestination(),
        optionUiStates = this.options.map { it.toOption() },
        originUiState = this.origin.toOrigin(),
        distance = this.distance,
        duration = this.duration
    )
}

private fun Destination.toDestination(): DestinationUiState {
    return DestinationUiState(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun Origin.toOrigin(): OriginUiState {
    return OriginUiState(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun Option.toOption(): OptionUiState {
    return OptionUiState(
        description = this.description,
        id = this.id,
        name = this.name,
        rating = this.review.rating,
        value = this.value,
        vehicle = this.vehicle
    )
}