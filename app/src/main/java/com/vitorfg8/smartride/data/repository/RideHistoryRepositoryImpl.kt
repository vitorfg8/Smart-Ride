package com.vitorfg8.smartride.data.repository

import com.vitorfg8.smartride.data.api.RideService
import com.vitorfg8.smartride.data.model.ridehistory.toRideHistory
import com.vitorfg8.smartride.domain.repository.RideHistoryRepository
import kotlinx.coroutines.flow.flow

class RideHistoryRepositoryImpl(private val rideHistoryService: RideService) :
    RideHistoryRepository {
    override suspend fun getRideHistory(
        customerId: String,
        driverId: Int?
    ) = flow {
        emit(rideHistoryService.getRideHistory(customerId, driverId).toRideHistory())
    }
}