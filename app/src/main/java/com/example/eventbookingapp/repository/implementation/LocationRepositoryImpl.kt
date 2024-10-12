package com.example.eventbookingapp.repository.implementation

import android.location.Location
import android.os.Parcel
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.locationDataStore
import com.example.eventbookingapp.config.locationKey
import com.example.eventbookingapp.repository.repository_interface.LocationRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LocationRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): LocationRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCurrentLocation(): Flow<Location?> {
        val context = EventBookingApplication.applicationContext()

        return context.locationDataStore.data.flatMapLatest { pref ->
            flow {
                val data = pref[stringPreferencesKey(locationKey)]
                val location: Location? = if (data == null) {
                    null
                } else {
                    val list = data.split(",")
                    val convert = Location(list[0])

                    convert.latitude = list[1].toDouble()
                    convert.longitude = list[2].toDouble()
                    convert.altitude = list[3].toDouble()
                    convert.accuracy = list[4].toFloat()

                    convert
                }

                emit(location)
            }
        }
    }

    override fun updateCurrentLocation(location: Location) {
        val context = EventBookingApplication.applicationContext()

        CoroutineScope(ioDispatcher).launch {
            context.locationDataStore.edit {
                it[stringPreferencesKey(locationKey)] = locationToString(location)
            }
        }
    }

    private fun locationToString(location: Location): String {
        return "${location.provider},${location.latitude},${location.longitude},${location.altitude},${location.accuracy}"
    }
}