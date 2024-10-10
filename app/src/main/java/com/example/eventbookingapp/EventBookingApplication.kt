package com.example.eventbookingapp

import android.app.Application
import android.content.Context
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.logging.Logger

@HiltAndroidApp
class EventBookingApplication: Application() {
    companion object {
        private lateinit var instance: EventBookingApplication

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        KakaoMapSdk.init(this, BuildConfig.kakaoNativeAppKey)
    }
}