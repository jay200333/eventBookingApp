package com.example.eventbookingapp.module

import android.location.Location
import com.example.eventbookingapp.module.impl.LocationModuleImpl
import com.example.eventbookingapp.repository.repository_interface.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CustomModule {
    @Provides
    @Singleton
    fun provideLocationModule(locationRepository: LocationRepository): LocationModuleInterface = LocationModuleImpl(locationRepository)
}