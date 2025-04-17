package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Entity(
    val data: Map<String, @RawValue Any?>,
    val description: String? = null
) : Parcelable
