package com.example.eventbookingapp.module

import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.cacheSize
import com.example.eventbookingapp.config.interceptors.AuthInterceptor
import com.example.eventbookingapp.config.interceptors.LoggingInterceptor
import com.example.eventbookingapp.repository.repository_interface.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenRepository: TokenRepository): AuthInterceptor {
        return AuthInterceptor(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        /**
        Set File application cache file directory
        size : 3MB
         */
        val cache = Cache(
            EventBookingApplication.applicationContext().cacheDir,
            cacheSize
        )

        return OkHttpClient()
            .newBuilder()
            .cache(cache)
            .addInterceptor(LoggingInterceptor())
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit2Client(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}