package com.example.eventbookingapp.repository.implementation

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.tokenKey
import com.example.eventbookingapp.config.userDataStore
import com.example.eventbookingapp.repository.repository_interface.TokenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class TokenRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TokenRepository {
    override suspend fun getToken(): String {
        val context = EventBookingApplication.applicationContext()
        return withContext(ioDispatcher) {
            val pref = context.userDataStore.data.first()
            pref[stringPreferencesKey(tokenKey)] ?: ""
        }
    }

    override fun writeToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun wipeToken() {
        TODO("Not yet implemented")
    }
}