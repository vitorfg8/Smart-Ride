package com.vitorfg8.smartride.di

import com.vitorfg8.smartride.data.api.RideEstimateService
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

private fun provideRideEstimateService(retrofit: Retrofit): RideEstimateService {
    return retrofit.create(RideEstimateService::class.java)
}