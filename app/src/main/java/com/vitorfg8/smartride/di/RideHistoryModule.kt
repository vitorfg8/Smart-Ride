package com.vitorfg8.smartride.di

import com.vitorfg8.smartride.ui.ridehistory.RideHistoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rideHistoryModule = module {
    viewModel {
        RideHistoryViewModel()
    }
}