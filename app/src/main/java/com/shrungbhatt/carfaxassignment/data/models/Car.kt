package com.shrungbhatt.carfaxassignment.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "car")
data class Car(
    @PrimaryKey @field:SerializedName("id") val id: String,
    @field:SerializedName("dealer") val dealer: Dealer?,
    @field:SerializedName("vin") val vin: String?,
    @field:SerializedName("mileage") val mileage: Double?,
    @field:SerializedName("currentPrice") val currentPrice: Double?,
    @field:SerializedName("exteriorColor") val exteriorColor: String?,
    @field:SerializedName("interiorColor") val interiorColor: String?,
    @field:SerializedName("engine") val engine: String?,
    @field:SerializedName("displacement") val displacement: String?,
    @field:SerializedName("driveType") val driveType: String?,
    @field:SerializedName("transmission") val transmission: String?,
    @field:SerializedName("bodyType") val bodyType: String?,
    @field:SerializedName("images") val images: Images?,
    @field:SerializedName("year") val year: Int?,
    @field:SerializedName("make") val make: String?,
    @field:SerializedName("model") val model: String?,
    @field:SerializedName("trim") val trim: String?
) : Parcelable {
    val dealerPhoneNumber: String
        get() {
            return dealer?.phone ?: ""
        }

    val imageUrlSmall: String
        get() {
            return images?.map?.get("small") ?: ""
        }

    val imageUrlLarge: String
        get() {
            return images?.map?.get("large") ?: ""
        }

    val dealerCity: String
        get() {
            return dealer?.city ?: ""
        }

    val dealerState: String
        get() {
            return dealer?.state ?: ""
        }
}

@Parcelize
data class Dealer(
    val phone: String?,
    val city: String?,
    val state: String?
): Parcelable

@Parcelize
data class Images(
    @field:SerializedName("firstPhoto") val map: Map<String, String>?
):Parcelable
