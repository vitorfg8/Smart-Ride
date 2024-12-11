package com.vitorfg8.smartride.data.repository

import com.vitorfg8.smartride.data.api.RideService
import com.vitorfg8.smartride.data.model.rideconfirm.toRideConfirm
import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmRequest
import com.vitorfg8.smartride.domain.model.rideconfirm.RideConfirmResponse
import com.vitorfg8.smartride.domain.model.rideconfirm.toRideConfirmDto
import com.vitorfg8.smartride.domain.repository.RideConfirmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RideConfirmRepositoryImpl(
    private val rideService: RideService
) : RideConfirmRepository {
    override suspend fun confirmRide(request: RideConfirmRequest): Flow<RideConfirmResponse> {
        return flow {
            emit(rideService.confirmRide(request.toRideConfirmDto()).toRideConfirm())
        }
    }
}