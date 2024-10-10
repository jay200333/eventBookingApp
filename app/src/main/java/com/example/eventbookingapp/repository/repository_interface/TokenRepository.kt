package com.example.eventbookingapp.repository.repository_interface

interface TokenRepository {
    suspend fun getToken(): String
    fun writeToken(token: String)
    fun wipeToken()
}