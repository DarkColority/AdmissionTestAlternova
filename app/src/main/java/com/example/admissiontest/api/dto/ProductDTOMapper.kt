package com.example.admissiontest.api.dto

import com.example.admissiontest.model.Product

class ProductDTOMapper {

    private fun fromProductDTOToProductDomain(productDTO: ProductDTO): Product {
        return Product(productDTO.id,
            productDTO.name,
            productDTO.unitPrice,
            productDTO.stock,
            productDTO.imageURL,
            productDTO.description)
    }
    fun fromProductDTOListToProductDomainList(productDTOList: List<ProductDTO>): List<Product> {
        return productDTOList.map { fromProductDTOToProductDomain(it)}
    }
    fun fromProductDetailDTOToProductDomain(productDetailDTO: ProductDTO): Product {
        return fromProductDTOToProductDomain(productDetailDTO)
    }

}