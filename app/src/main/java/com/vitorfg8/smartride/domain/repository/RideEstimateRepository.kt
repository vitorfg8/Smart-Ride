package com.vitorfg8.smartride.domain.repository

import com.vitorfg8.smartride.domain.model.riderequest.RideEstimate
import kotlinx.coroutines.flow.Flow

interface RideEstimateRepository {

    suspend fun estimateRide(
        customerId: String,
        origin: String,
        destination: String
    ): Flow<RideEstimate>

}