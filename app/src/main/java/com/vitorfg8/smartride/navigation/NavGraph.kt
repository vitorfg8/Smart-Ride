package com.vitorfg8.smartride.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vitorfg8.smartride.ui.ridehistory.RideHistoryScreen
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsEvent
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsScreen
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiStateType
import com.vitorfg8.smartride.ui.riderequest.RideRequestEvent.NavigateToRideOptions
import com.vitorfg8.smartride.ui.riderequest.RideRequestScreen
import com.vitorfg8.smartride.ui.riderequest.RideRequestViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Screens.RideRequest
    ) {
        composable<Screens.RideRequest> {
            val viewModel: RideRequestViewModel = koinViewModel<RideRequestViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            RideRequestScreen(uiState = uiState.value, onEvent = { event ->
                when (event) {
                    is NavigateToRideOptions -> {
                        navController.navigate(
                            Screens.RideOptions(
                                event.rideOptions
                            )
                        )
                    }

                    else -> viewModel.onEvent(event)
                }
            })
        }

        composable<Screens.RideOptions>(
            typeMap = mapOf(typeOf<RideOptionsUiState>() to RideOptionsUiStateType.rideOptionsUiStateType)
        ) {
            val args = it.toRoute<Screens.RideOptions>()
            RideOptionsScreen(uiState = args.rideOptionsUiState, onEvent = { event ->
                when (event) {
                    is RideOptionsEvent.GoBack -> {
                        navController.popBackStack()
                    }

                    is RideOptionsEvent.NavigateToHistory -> {
                        navController.navigate(Screens.RideHistory)
                    }
                }
            })
        }

        composable<Screens.RideHistory> {
            RideHistoryScreen()
        }
    }
}


object Screens {
    @Serializable
    object RideRequest

    @Serializable
    data class RideOptions(
        val rideOptionsUiState: RideOptionsUiState
    )

    @Serializable
    object RideHistory
}