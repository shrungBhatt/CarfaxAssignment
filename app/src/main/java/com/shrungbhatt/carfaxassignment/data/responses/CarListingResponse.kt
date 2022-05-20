package com.shrungbhatt.carfaxassignment.data.responses

import com.google.gson.annotations.SerializedName
import com.shrungbhatt.carfaxassignment.data.models.Car

data class CarListingResponse(
    @field:SerializedName("listings") val cars: List<Car>
)