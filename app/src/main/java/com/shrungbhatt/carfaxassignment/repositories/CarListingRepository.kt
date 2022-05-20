package com.shrungbhatt.carfaxassignment.repositories

import com.shrungbhatt.carfaxassignment.api.INetworkService
import com.shrungbhatt.carfaxassignment.data.CarDao
import com.shrungbhatt.carfaxassignment.data.models.Car
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

    private val channel = Channel<Flow<List<Car>>>()
    val latestData: Flow<Flow<List<Car>>> = channel.receiveAsFlow()

    suspend fun fetchCars() {
        /*
        Load the cars from the database first and subscribe to the flow,
         so that when the new data is added, the flow receives the new data
        */
        channel.send(carDao.getCars())

        //Get the latest car from network, and update the database and catch errors
        try {
            val response = service.getCars()
            carDao.saveCars(response.cars)
        } catch (e: Exception) {
            eventChannel.emitEvent(EventType.ERROR, e.message ?: "Unknown Error")
        }

    }

}