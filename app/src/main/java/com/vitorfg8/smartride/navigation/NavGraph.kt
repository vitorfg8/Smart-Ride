package com.vitorfg8.smartride.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vitorfg8.smartride.ui.RideRequestScreen
import com.vitorfg8.smartride.ui.RideRequestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.RideRequest.route) {
        composable(Route.RideRequest.route) {
            val viewModel: RideRequestViewModel = koinViewModel<RideRequestViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
            RideRequestScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
        composable(Route.RideOptions.route) {
            //TODO
        }
        composable(Route.RideHistory.route) {
            //TODO
        }
    }
}