package com.vitorfg8.smartride

import android.app.Application
import com.vitorfg8.smartride.di.apiModule
import com.vitorfg8.smartride.di.rideOptionsModule
import com.vitorfg8.smartride.di.rideRequestModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(apiModule, rideRequestModule, rideOptionsModule)
            )
        }
    }
}