package com.example.admissiontest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val unitPrice: Int,
    var stock: Int,
    val imageURL: String,
    val description: String?
) : Parcelable
