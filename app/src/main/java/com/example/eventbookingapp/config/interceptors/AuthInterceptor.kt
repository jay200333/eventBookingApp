package com.example.eventbookingapp.config.interceptors

import android.content.Context
import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.headerTokenKey
import com.example.eventbookingapp.repository.repository_interface.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = runBlocking {
            tokenRepository.getToken()
        }

        val request = chain.request().newBuilder()
            .addHeader(headerTokenKey, token)
            .build()

        return chain.proceed(request)
    }
}