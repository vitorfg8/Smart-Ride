package com.vitorfg8.smartride.data.model.riderequest


import com.google.gson.annotations.SerializedName
import com.vitorfg8.smartride.domain.model.riderequest.Destination
import com.vitorfg8.smartride.domain.model.riderequest.Option
import com.vitorfg8.smartride.domain.model.riderequest.Origin
import com.vitorfg8.smartride.domain.model.riderequest.Review
import com.vitorfg8.smartride.domain.model.riderequest.RideEstimate

data class RideEstimateResponseDto(
    @SerializedName("destination")
    val destinationDto: DestinationDto,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("options")
    val optionRespons: List<OptionDto>,
    @SerializedName("origin")
    val originDto: OriginDto,
    @SerializedName("routeResponse")
    val routeResponse: RouteDto,
    @SerializedName("error_code")
    val errorCode: String?,
    @SerializedName("error_description")
    val errorDescription: String,
)

fun RideEstimateResponseDto.toRideEstimate(): RideEstimate {
    return RideEstimate(
        destination = this.destinationDto.toDestination(),
        distance = this.distance,
        duration = this.duration,
        options = this.optionRespons.map { it.toOption() },
        origin = this.originDto.toOrigin(),
        errorCode = this.errorCode,
        errorDescription = this.errorDescription
    )
}

private fun DestinationDto.toDestination(): Destination {
    return Destination(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun OriginDto.toOrigin(): Origin {
    return Origin(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun OptionDto.toOption(): Option {
    return Option(
        description = this.description,
        id = this.id,
        name = this.name,
        review = this.reviewDto.toReview(),
        value = this.value,
        vehicle = this.vehicle
    )
}

private fun ReviewDto.toReview(): Review {
    return Review(
        comment = this.comment,
        rating = this.rating
    )
}