package com.vitorfg8.smartride.domain.repository

import com.vitorfg8.smartride.domain.model.ridehistory.RideHistory
import kotlinx.coroutines.flow.Flow

interface RideHistoryRepository {
    suspend fun getRideHistory(customerId: String, driverId: Int? = null): Flow<RideHistory>
}