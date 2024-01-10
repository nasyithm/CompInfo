package com.nasyith.compinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val description: String,
    val processor: String,
    val memory: String,
    val videoGraphic: String,
    val storage: String,
    val photo: String
) : Parcelable
