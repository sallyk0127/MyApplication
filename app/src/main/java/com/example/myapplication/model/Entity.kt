package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entity(
    val data: Map<String, String?> = emptyMap()
) : Parcelable
