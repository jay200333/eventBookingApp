package com.example.eventbookingapp.module

import com.example.eventbookingapp.EventBookingApplication
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
        .build()

    private fun initClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .client(interceptorClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

//class OkhttpInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val builder = chain.request().newBuilder()
//
//    }
//
//}