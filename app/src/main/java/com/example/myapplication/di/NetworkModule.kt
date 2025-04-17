package com.example.myapplication.di

import com.example.myapplication.model.Entity
import com.example.myapplication.model.EntityDeserializer
import com.example.myapplication.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.myapplication.ui.login.AuthRepository

// Dagger Hilt module that provides all network-related dependencies.

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL of the backend API
    private const val BASE_URL = "https://nit3213api.onrender.com/"

    // Retrofit Singleton Gson instance converts JSON to Kotlin objects.
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Entity::class.java, EntityDeserializer())
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository =
        AuthRepository(apiService)
}
