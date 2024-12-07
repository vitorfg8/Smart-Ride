package com.vitorfg8.smartride.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vitorfg8.smartride.ui.RideRequestScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "ride_request") {
        composable(Route.RideRequest.route) {
            RideRequestScreen()
        }
        composable(Route.RideOptions.route) {
            //TODO
        }
        composable(Route.RideHistory.route) {
            //TODO
        }
    }
}