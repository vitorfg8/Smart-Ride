package com.vitorfg8.smartride.domain.repository

import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmRequest
import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmResponse
import kotlinx.coroutines.flow.Flow

interface RideConfirmRepository {
    suspend fun confirmRide(request: RideConfirmRequest): Flow<RideConfirmResponse>
}