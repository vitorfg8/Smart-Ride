package com.vitorfg8.smartride.data.repository

import com.vitorfg8.smartride.data.api.RideEstimateService
import com.vitorfg8.smartride.data.model.riderequest.RideEstimateRequest
import com.vitorfg8.smartride.data.model.riderequest.toRideEstimate
import com.vitorfg8.smartride.domain.repository.RideEstimateRepository
import kotlinx.coroutines.flow.flow

class RideEstimateRepositoryImpl(val rideEstimateService: RideEstimateService) :
    RideEstimateRepository {
    override suspend fun estimateRide(
        customerId: String, origin: String, destination: String
    ) = flow {
        emit(
            rideEstimateService.estimateRide(RideEstimateRequest(customerId, origin, destination))
                .toRideEstimate()
        )
    }
}