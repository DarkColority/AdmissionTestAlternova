package com.example.admissiontest.api

import com.example.admissiontest.BASE_URL
import com.example.admissiontest.GET_ALL_PRODUCTS
import com.example.admissiontest.GET_PRODUCT_DETAIL
import com.example.admissiontest.api.response.ProductDetailResponse
import com.example.admissiontest.api.response.ProductListApiResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService{
    @GET(GET_ALL_PRODUCTS)
    suspend fun getAllProducts(): ProductListApiResponse

    @GET(GET_PRODUCT_DETAIL)
    suspend fun getProductDetail(@Query("id")idProduct: Int): ProductDetailResponse

}

object ProductsApi{
    val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}