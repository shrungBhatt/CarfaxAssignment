package com.shrungbhatt.carfaxassignment.modules

import com.shrungbhatt.carfaxassignment.api.INetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModules {

    @Singleton
    @Provides
    fun provideNetworkService(): INetworkService{
        return INetworkService.create()
    }

}