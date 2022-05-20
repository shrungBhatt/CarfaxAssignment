package com.shrungbhatt.carfaxassignment.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shrungbhatt.carfaxassignment.data.models.Dealer
import com.shrungbhatt.carfaxassignment.data.models.Images

class Converters {
    @TypeConverter
    fun toDealer(value: String): Dealer =
        Gson().fromJson(value, object : TypeToken<Dealer>() {}.type)

    @TypeConverter
    fun fromDealer(value: Dealer): String =
        Gson().toJson(value)

    @TypeConverter
    fun toImages(value: String): Images =
        Gson().fromJson(value, object : TypeToken<Images>() {}.type)

    @TypeConverter
    fun toImages(value: Images): String =
        Gson().toJson(value)
}