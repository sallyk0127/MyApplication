package com.example.myapplication.network

import com.example.myapplication.model.AuthResponse
import com.example.myapplication.model.DashboardResponse
import retrofit2.http.*

interface ApiService {

    @POST("{campus}/auth")
    suspend fun login(
        @Path("campus") campus: String,
        @Body body: Map<String, String>
    ): AuthResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(
        @Path("keypass") keypass: String
    ): DashboardResponse
}
