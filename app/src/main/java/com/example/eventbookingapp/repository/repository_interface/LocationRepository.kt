package com.example.eventbookingapp.repository.repository_interface

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getCurrentLocation(): Flow<Location?>
    fun updateCurrentLocation(location: Location)
}