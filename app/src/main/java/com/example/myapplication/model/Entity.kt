package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entity(
    val property1: String,
    val property2: String,
    val description: String
) : Parcelable
