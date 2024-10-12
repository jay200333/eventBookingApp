package com.example.eventbookingapp.view.main.viewmodel

import android.location.Location
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventbookingapp.MainActivity
import com.example.eventbookingapp.module.LocationModuleInterface
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val locationModule: LocationModuleInterface
): ViewModel() {
    // SharingStarted.Eagerly 적용 시 구독자가 없어도 데이터를 가져옴
    val currentLocation: StateFlow<Location?> =
        locationModule.getCurrentLocation().stateIn(viewModelScope, SharingStarted.Eagerly, null)
}