package com.vitorfg8.smartride.data.api

import com.vitorfg8.smartride.data.model.rideconfirm.RideConfirmRequestDto
import com.vitorfg8.smartride.data.model.rideconfirm.RideConfirmResponseDto
import com.vitorfg8.smartride.data.model.riderequest.RideEstimateRequestDto
import com.vitorfg8.smartride.data.model.riderequest.RideEstimateResponseDto
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface RideService {
    @POST("/ride/estimate")
    suspend fun estimateRide(
        @Body request: RideEstimateRequestDto
    ): RideEstimateResponseDto

    @PATCH("/ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmRequestDto
    ): RideConfirmResponseDto
}