package com.example.eventbookingapp.module

import android.Manifest
import android.location.Location
import androidx.activity.ComponentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.Flow

interface LocationModuleInterface {
    fun syncCurrentLocation(client: FusedLocationProviderClient)
    fun getCurrentLocation(): Flow<Location?>
    fun checkPermissionApprove(): String
}