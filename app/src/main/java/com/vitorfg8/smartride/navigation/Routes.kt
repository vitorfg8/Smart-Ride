package com.vitorfg8.smartride.navigation

sealed class Route(val route: String) {
    object RideRequest : Route("ride_request")
    object RideOptions : Route("ride_options")
    object RideHistory : Route("ride_history")
}