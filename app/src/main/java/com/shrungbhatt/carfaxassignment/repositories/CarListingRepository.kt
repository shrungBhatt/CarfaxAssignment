package com.shrungbhatt.carfaxassignment.repositories

import com.shrungbhatt.carfaxassignment.api.INetworkService
import javax.inject.Inject

class CarListingRepository @Inject constructor(
    private val service: INetworkService
) {
}