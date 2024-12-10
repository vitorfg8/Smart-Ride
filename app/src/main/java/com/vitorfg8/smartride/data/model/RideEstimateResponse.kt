package com.vitorfg8.smartride.data.model


import com.google.gson.annotations.SerializedName
import com.vitorfg8.smartride.domain.model.riderequest.Destination
import com.vitorfg8.smartride.domain.model.riderequest.Option
import com.vitorfg8.smartride.domain.model.riderequest.Origin
import com.vitorfg8.smartride.domain.model.riderequest.Review
import com.vitorfg8.smartride.domain.model.riderequest.RideEstimate

data class RideEstimateResponse(
    @SerializedName("destination")
    val destinationResponse: DestinationResponse,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("options")
    val optionResponses: List<OptionResponse>,
    @SerializedName("origin")
    val originResponse: OriginResponse,
    @SerializedName("routeResponse")
    val routeResponse: RouteResponse,
    @SerializedName("error_code")
    val errorCode: String?,
    @SerializedName("error_description")
    val errorDescription: String,
)

fun RideEstimateResponse.toRideEstimate(): RideEstimate {
    return RideEstimate(
        destination = this.destinationResponse.toDestination(),
        distance = this.distance,
        duration = this.duration,
        options = this.optionResponses.map { it.toOption() },
        origin = this.originResponse.toOrigin(),
        errorCode = this.errorCode,
        errorDescription = this.errorDescription
    )
}

private fun DestinationResponse.toDestination(): Destination {
    return Destination(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun OriginResponse.toOrigin(): Origin {
    return Origin(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun OptionResponse.toOption(): Option {
    return Option(
        description = this.description,
        id = this.id,
        name = this.name,
        review = this.reviewResponse.toReview(),
        value = this.value,
        vehicle = this.vehicle
    )
}

private fun ReviewResponse.toReview(): Review {
    return Review(
        comment = this.comment,
        rating = this.rating
    )
}