package com.example.eventbookingapp.module

import android.location.Location

interface LocationModuleInterface {
    fun syncCurrentLocation(): Location
    fun getCurrentLocation(): Location
}