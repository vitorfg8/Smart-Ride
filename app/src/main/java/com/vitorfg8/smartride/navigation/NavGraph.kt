package com.vitorfg8.smartride.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vitorfg8.smartride.ui.ridehistory.RideHistoryScreen
import com.vitorfg8.smartride.ui.ridehistory.RideHistoryViewModel
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsEvent
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsScreen
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiState
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsUiStateType
import com.vitorfg8.smartride.ui.rideoptions.RideOptionsViewModel
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
        navController = navController, startDestination = Screens.RideRequestScreen
    ) {
        composable<Screens.RideRequestScreen> {
            val viewModel: RideRequestViewModel = koinViewModel<RideRequestViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            RideRequestScreen(uiState = uiState.value, onEvent = { event ->
                when (event) {
                    is NavigateToRideOptions -> {
                        navController.navigate(
                            Screens.RideOptionsScreen(
                                event.rideOptions,
                                event.customerId,
                                event.origin,
                                event.destination,
                            )
                        )
                    }
                    else -> viewModel.onEvent(event)
                }
            })
        }

        composable<Screens.RideOptionsScreen>(
            typeMap = mapOf(typeOf<RideOptionsUiState>() to RideOptionsUiStateType.rideOptionsUiStateType)
        ) {
            val args = it.toRoute<Screens.RideOptionsScreen>()
            val viewModel: RideOptionsViewModel = koinViewModel<RideOptionsViewModel>()
            viewModel.onEvent(RideOptionsEvent.UpdateState(args.rideOptionsUiState))
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            RideOptionsScreen(
                uiState = uiState.value,
                customerId = args.customerId,
                origin = args.origin,
                destination = args.destination,
                onEvent = { event ->
                when (event) {
                    is RideOptionsEvent.GoBack -> {
                        navController.popBackStack()
                    }

                    is RideOptionsEvent.NavigateToHistory -> {
                        navController.navigate(Screens.RideHistoryScreen)
                    }
                    else -> Unit
                }
            })
        }

        composable<Screens.RideHistoryScreen> {
            val viewModel: RideHistoryViewModel = koinViewModel<RideHistoryViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            RideHistoryScreen(
                uiState = uiState.value,
                onEvent = viewModel::onEvent
            )
        }
    }
}


object Screens {
    @Serializable
    object RideRequestScreen

    @Serializable
    data class RideOptionsScreen(
        val rideOptionsUiState: RideOptionsUiState,
        val customerId: String,
        val origin: String,
        val destination: String
    )

    @Serializable
    object RideHistoryScreen
}