package com.example.myapplication.ui.login

import com.example.myapplication.model.LoginRequest
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.network.ApiService

class AuthRepository(
    private val apiService: ApiService
) {
    // Network request to the /{campus}/auth endpoint
    suspend fun login(campus: String, username: String, password: String): LoginResponse {
        val request = LoginRequest(username = username, password = password)
        return apiService.login(campus, request)
    }
}
