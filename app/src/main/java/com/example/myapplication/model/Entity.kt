package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entity(
    val exerciseName: String,
    val muscleGroup: String,
    val equipment: String,
    val difficulty: String,
    val caloresBurnedPerHour: String,
    val description: String
) : Parcelable
