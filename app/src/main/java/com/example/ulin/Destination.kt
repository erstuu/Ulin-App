package com.example.ulin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val title: String,
    val description: String,
    val location: String,
    val rating: String,
    val image: Int,
    val isRecommended: Boolean
) : Parcelable
