package com.shrungbhatt.carfaxassignment.api

import com.shrungbhatt.carfaxassignment.data.responses.CarListingResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {

    //@Todo: Change the response type
    @GET("assignment.json")
    suspend fun getCars(): CarListingResponse

    companion object {
        private const val BASE_URL = "https://carfax-for-consumers.firebaseio.com/"

        fun create(): INetworkService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(INetworkService::class.java)
        }
    }

}