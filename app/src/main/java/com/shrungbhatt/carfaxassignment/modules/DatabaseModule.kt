package com.shrungbhatt.carfaxassignment.modules

import android.content.Context
import com.shrungbhatt.carfaxassignment.data.CarDao
import com.shrungbhatt.carfaxassignment.data.CarfaxDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): CarfaxDatabase{
        return CarfaxDatabase.getInstance(context)
    }

    @Provides
    fun provideCarDao(newsDatabase: CarfaxDatabase): CarDao{
        return newsDatabase.getCarDao()
    }


}