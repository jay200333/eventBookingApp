package com.example.eventbookingapp.module.impl

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.exceptions.NotFoundPermissionException
import com.example.eventbookingapp.config.logger
import com.example.eventbookingapp.module.LocationModuleInterface
import com.example.eventbookingapp.repository.repository_interface.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationModuleImpl @Inject constructor(
    private val locationRepository: LocationRepository
): LocationModuleInterface {

    /**
     * 현재 위치 정보를 갱신하는 함수
     * 위치 정보 데이터는 기본적으로 dataStore를 통해 받을 수 있도록 구조화
     */
    @SuppressLint("MissingPermission")
    override fun syncCurrentLocation(client: FusedLocationProviderClient) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, 10000L)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return
                for (location in locationResult.locations){
                    locationRepository.updateCurrentLocation(location)
                    Log.d("latlng", "latlng :: latitude - ${location.latitude}, longitude - ${location.longitude}")
                    client.removeLocationUpdates(this)
                }
            }
        }

        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
    /**
     * 현재 위치 정보를 Flow로 받을 수 있도록 구조화
     * Flow의 데이터가 바뀔 때 데이터를 갱신할 수 있도록 하기
     */
    override fun getCurrentLocation(): Flow<Location?> {
        return locationRepository.getCurrentLocation()
    }

    @Throws(NotFoundPermissionException::class)
    override fun checkPermissionApprove(): String {
        val context = EventBookingApplication.applicationContext()
        val coarseCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (coarseCheck == PackageManager.PERMISSION_GRANTED)
            return Manifest.permission.ACCESS_COARSE_LOCATION

        val fineCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (fineCheck == PackageManager.PERMISSION_GRANTED)
            return Manifest.permission.ACCESS_COARSE_LOCATION

        throw NotFoundPermissionException("수락된 권한이 없습니다.")
    }

    private fun updateCurrentLocation() {

    }
}