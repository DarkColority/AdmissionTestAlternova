package com.example.admissiontest.api.dto

import com.squareup.moshi.Json

class ProductDTO (
    val id: Int,
    val name: String,
    @field:Json(name = "unit_price")val unitPrice: Int,
    val stock: Int,
    @field:Json(name = "image")val imageURL: String,
    @field:Json(name = "descripcion") val description: String?
    ){

}


