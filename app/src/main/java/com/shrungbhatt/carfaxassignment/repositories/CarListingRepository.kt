package com.shrungbhatt.carfaxassignment.repositories

import com.shrungbhatt.carfaxassignment.api.INetworkService
import com.shrungbhatt.carfaxassignment.data.CarDao
import com.shrungbhatt.carfaxassignment.data.models.Car
import com.shrungbhatt.carfaxassignment.data.responses.CarListingResponse
import com.shrungbhatt.carfaxassignment.util.EventType
import com.shrungbhatt.carfaxassignment.util.IEventChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class CarListingRepository @Inject constructor(
    private val service: INetworkService,
    private val carDao: CarDao,
    val eventChannel: IEventChannel
) {

    private val channel = Channel<List<Car>>()

    val latestData: Flow<List<Car>> = channel.receiveAsFlow()

    suspend fun fetchCars() {

        val response: CarListingResponse
        try {
            response = service.getCars()
            carDao.saveCars(response.cars)
        } catch (e: Exception) {
            eventChannel.emitEvent(EventType.ERROR, e.message ?: "Unknown Error")
        }

        //Get the cars from the database
        val cars = carDao.getCars()
        channel.send(cars)
    }

}