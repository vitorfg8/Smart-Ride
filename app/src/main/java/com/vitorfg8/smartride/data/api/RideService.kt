package com.vitorfg8.smartride.data.api

import com.vitorfg8.smartride.data.model.rideconfirm.RideConfirmRequestDto
import com.vitorfg8.smartride.data.model.rideconfirm.RideConfirmResponseDto
import com.vitorfg8.smartride.data.model.ridehistory.RideHistoryDto
import com.vitorfg8.smartride.data.model.riderequest.RideEstimateRequestDto
import com.vitorfg8.smartride.data.model.riderequest.RideEstimateResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RideService {
    @POST("/ride/estimate")
    suspend fun estimateRide(
        @Body request: RideEstimateRequestDto
    ): RideEstimateResponseDto

    @PATCH("ride/confirm")
    suspend fun confirmRide(
        @Body request: RideConfirmRequestDto
    ): RideConfirmResponseDto

    @GET("/ride/{customer_id}")
    suspend fun getRideHistory(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: Int?
    ): RideHistoryDto
}