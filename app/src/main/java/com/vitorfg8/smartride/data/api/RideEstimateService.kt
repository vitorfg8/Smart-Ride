package com.vitorfg8.smartride.data.api

import com.vitorfg8.smartride.data.model.RideEstimateRequest
import com.vitorfg8.smartride.data.model.RideEstimateResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RideEstimateService {
    @POST("/ride/estimate")
    suspend fun estimateRide(
        @Body request: RideEstimateRequest
    ): RideEstimateResponse
}