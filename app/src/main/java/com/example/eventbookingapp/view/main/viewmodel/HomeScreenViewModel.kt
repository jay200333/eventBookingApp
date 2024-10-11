package com.example.eventbookingapp.view.main.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.example.eventbookingapp.MainActivity
import com.example.eventbookingapp.module.LocationModuleInterface
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val locationModule: LocationModuleInterface
): ViewModel() {

    fun onUpdateLocationRequest(client: FusedLocationProviderClient) {
        val currentState = locationModule.syncCurrentLocation(client)


    }
}