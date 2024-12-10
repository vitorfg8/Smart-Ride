package com.vitorfg8.smartride.di

import com.vitorfg8.smartride.ui.rideoptions.RideOptionsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rideOptionsModule = module {
    viewModel {
        RideOptionsViewModel()
    }
}