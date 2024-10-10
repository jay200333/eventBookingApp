package com.example.eventbookingapp.module

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Retrofit2Client @Inject constructor(
    private val retrofit2Client: Retrofit
) {
    val client: Retrofit = retrofit2Client
}