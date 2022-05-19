package com.shrungbhatt.carfaxassignment.data.models

import com.google.gson.annotations.SerializedName

data class Car(
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("dealer") val dealer: Dealer?,
    @field:SerializedName("vin") val vin: String?,
    @field:SerializedName("mileage") val mileage: Long?,
    @field:SerializedName("currentPrice") val currentPrice: Long?,
    @field:SerializedName("exteriorColor") val exteriorColor: String?,
    @field:SerializedName("interiorColor") val interiorColor: String?,
    @field:SerializedName("engine") val engine: String?,
    @field:SerializedName("displacement") val displacement: String?,
    @field:SerializedName("driveType") val driveType: String?,
    @field:SerializedName("transmission") val transmission: String?,
    @field:SerializedName("bodyType") val bodyType: String?,
    @field:SerializedName("images") val images: Images?,
    @field:SerializedName("year") val year: Long?,
    @field:SerializedName("make") val make: String?,
    @field:SerializedName("model") val model: String?,
    @field:SerializedName("trim") val trim: String?
) {
    val phoneNumber: String
        get() {
            return dealer?.phone ?: ""
        }

    val imageUrl: String
        get() {
            return images?.map?.get("large") ?: ""
        }

    val dealerCity: String
        get(){
            return dealer?.city ?: ""
        }

    val dealerState: String
        get(){
            return dealer?.state ?: ""
        }
}

data class Dealer(
    val phone: String?,
    val city: String?,
    val state: String?
)

data class Images(
    @field:SerializedName("firstPhoto") val map: HashMap<String, String>?
)
