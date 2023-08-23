package com.example.admissiontest.api.repository

import com.example.admissiontest.api.ApiResponseStatus
import com.example.admissiontest.api.ProductsApi.retrofitService
import com.example.admissiontest.api.dto.ProductDTOMapper
import com.example.admissiontest.api.makeNetworkCall
import com.example.admissiontest.model.Product

class ProductRepository {
    suspend fun downloadProducts(): ApiResponseStatus<List<Product>> = makeNetworkCall {
        val productListApiResponse = retrofitService.getAllProducts()
        val productDTOList = productListApiResponse.products
        val productDTOMapper = ProductDTOMapper()
        productDTOMapper.fromProductDTOListToProductDomainList(productDTOList)
    }

    suspend fun downloadProductDetail(idProduct: Int): ApiResponseStatus<Product> = makeNetworkCall {
        val productDetailResponse = retrofitService.getProductDetail(idProduct)
        val productDetailDTO = productDetailResponse.product
        val productDTOMapper = ProductDTOMapper()
        productDTOMapper.fromProductDetailDTOToProductDomain(productDetailDTO)
    }
}