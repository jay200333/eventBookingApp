package com.example.eventbookingapp.config.interceptors

import android.util.Log
import com.example.eventbookingapp.config.logger
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("API CALL", "-------------------------------------------------------------\n")
        Log.d("API CALL", "Request URL\n${request.url}\n\n")
        Log.d("API CALL", "Request Body\n${request.body}\n\n")

        val response = chain.proceed(request)

        Log.d("API CALL", "Response body about ${request.url}\n${response.body}\n\n")
        Log.d("API CALL", "-----------------------------END----------------------------\n")

        return response
    }

}