package com.shrungbhatt.carfaxassignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.shrungbhatt.carfaxassignment.data.models.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveCars(cars: List<Car>)

    @Query("SELECT * FROM car")
    fun getCars(): Flow<List<Car>>
}
