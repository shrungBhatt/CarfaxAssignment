package com.shrungbhatt.carfaxassignment.modules

import com.shrungbhatt.carfaxassignment.util.EventChannel
import com.shrungbhatt.carfaxassignment.util.IEventChannel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UtilModules {

    @Provides
    fun provideEventChannel(): IEventChannel{
        return EventChannel()
    }

}