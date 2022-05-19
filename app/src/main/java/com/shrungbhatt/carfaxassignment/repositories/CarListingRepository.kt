package com.shrungbhatt.carfaxassignment.repositories

import com.shrungbhatt.carfaxassignment.api.INetworkService
import com.shrungbhatt.carfaxassignment.data.models.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarListingRepository @Inject constructor(
    private val service: INetworkService
) {

    val latestData: Flow<List<Car>> = flow {
        val latestList = service.getCars()
        emit(latestList.cars)
    }

}