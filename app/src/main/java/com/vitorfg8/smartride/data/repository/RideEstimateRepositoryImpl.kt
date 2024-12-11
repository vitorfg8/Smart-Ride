package com.vitorfg8.smartride.data.repository

import com.vitorfg8.smartride.data.api.RideService
import com.vitorfg8.smartride.data.model.riderequest.RideEstimateRequestDto
import com.vitorfg8.smartride.data.model.riderequest.toRideEstimate
import com.vitorfg8.smartride.domain.repository.RideEstimateRepository
import kotlinx.coroutines.flow.flow

class RideEstimateRepositoryImpl(val rideService: RideService) :
    RideEstimateRepository {
    override suspend fun estimateRide(
        customerId: String, origin: String, destination: String
    ) = flow {
        emit(
            rideService.estimateRide(RideEstimateRequestDto(customerId, origin, destination))
                .toRideEstimate()
        )
    }
}