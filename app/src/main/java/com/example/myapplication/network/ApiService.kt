package com.example.myapplication.network

import com.example.myapplication.model.DashboardResponse
import com.example.myapplication.model.LoginRequest
import com.example.myapplication.model.LoginResponse
import retrofit2.http.*

interface ApiService {

    @POST("{campus}/auth")
    suspend fun login(
        @Path("campus") campus: String,
        @Body request: LoginRequest
    ): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(
        @Path("keypass") keypass: String
    ): DashboardResponse
}
