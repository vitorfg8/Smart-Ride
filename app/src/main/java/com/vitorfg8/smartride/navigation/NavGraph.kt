package com.vitorfg8.smartride.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vitorfg8.smartride.ui.ridehistory.RideHistoryScreen
import com.vitorfg8.smartride.ui.rideoptions.DestinationUiState
import com.vitorfg8.smartride.ui.rideoptions.OptionUiState
import com.vitorfg8.smartride.ui.rideoptions.OriginUiState
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsEvent
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsScreen
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsViewModel
import com.vitorfg8.smartride.ui.riderequest.RideRequestEvent
import com.vitorfg8.smartride.ui.riderequest.RideRequestScreen
import com.vitorfg8.smartride.ui.riderequest.RideRequestViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RideRequestRoute) {
        composable<RideRequestRoute> {
            val viewModel: RideRequestViewModel = koinViewModel<RideRequestViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
            RideRequestScreen(
                uiState = uiState,
                onEvent = { event ->
                    when (event) {
                        is RideRequestEvent.NavigateToRideOptions -> {
                            navController.navigate(
                                RideOptionsRoute(
                                    destination = event.rideOptions.destinationUiState,
                                    option = event.rideOptions.optionUiStates,
                                    origin = event.rideOptions.originUiState
                                )
                            )
                        }

                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        composable<RideOptionsRoute> { backStackEntry ->
            val uiState = backStackEntry.toRoute<RideOptionsRoute>()
            val viewModel: RideOptionsViewModel = koinViewModel<RideOptionsViewModel>()
            RideOptionsScreen(
                uiState = RideOptionsUiState(
                    destinationUiState = uiState.destination,
                    optionUiStates = uiState.option,
                    originUiState = uiState.origin,
                ),
                onEvent = { event ->
                    when (event) {
                        is RideOptionsEvent.GoBack -> {
                            navController.popBackStack()
                        }

                        is RideOptionsEvent.NavigateToHistory -> {
                            //navController.navigate(RideHistoryRoute)
                        }

                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        composable<RideHistoryRoute> {
            RideHistoryScreen()
        }
    }

}

@Serializable

data class RideRequestRoute(
    val dummy: String = "" // Add a dummy parameter if required for serialization
)

@Serializable
data class RideOptionsRoute(
    val destination: DestinationUiState,
    val option: List<OptionUiState>,
    val origin: OriginUiState,
)

@Serializable
object RideHistoryRoute
