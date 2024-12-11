package com.vitorfg8.smartride.di

import com.vitorfg8.smartride.data.api.RideService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideRetrofit() }
    single { provideRideEstimateService(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideRideEstimateService(retrofit: Retrofit): RideService {
    return retrofit.create(RideService::class.java)
}