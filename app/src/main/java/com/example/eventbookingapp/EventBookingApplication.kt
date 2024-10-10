package com.example.eventbookingapp

import android.app.Application
import android.content.Context
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.logging.Logger

@HiltAndroidApp
class EventBookingApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoMapSdk.init(this, BuildConfig.kakaoNativeAppKey)
    }
}