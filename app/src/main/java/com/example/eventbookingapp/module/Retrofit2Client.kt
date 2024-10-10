package com.example.eventbookingapp.module

import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.logger
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit2Client {
    val client: Retrofit = initClient()
    private val interceptorClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(LoggingInterceptor())
        .build()

    private fun initClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .client(interceptorClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        logger.debug {
            "-------------------------------------------------------------\n"
        }

        logger.debug {
            "Request URL\n${request.url}\n\n"
        }

        logger.debug {
            "Request Body\n${request.body}\n\n"
        }

        val response = chain.proceed(request)

        logger.debug {
            "Response body about ${request.url}\n${response.body}\n\n"
        }

        logger.debug {
            "-----------------------------END----------------------------\n"
        }

        return response
    }

}