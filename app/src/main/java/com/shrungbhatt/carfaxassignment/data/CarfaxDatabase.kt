package com.shrungbhatt.carfaxassignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shrungbhatt.carfaxassignment.data.models.Car

private const val DATABASE_NAME = "carfax_db"

@Database(entities = [Car::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CarfaxDatabase: RoomDatabase() {
    abstract fun getCarDao(): CarDao

    companion object{
        @Volatile private var instance: CarfaxDatabase? = null

        fun getInstance(context: Context): CarfaxDatabase{
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CarfaxDatabase {
            return Room.databaseBuilder(context, CarfaxDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}