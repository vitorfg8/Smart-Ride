package com.vitorfg8.smartride.di

import com.vitorfg8.smartride.data.repository.RideEstimateRepositoryImpl
import com.vitorfg8.smartride.ui.riderequest.RideRequestViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rideRequestModule = module {
    viewModel {
        RideRequestViewModel(
            rideEstimateRepository = RideEstimateRepositoryImpl(
                rideEstimateService = get()
            )
        )
    }
}