package com.example.admissiontest.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admissiontest.api.ApiResponseStatus
import com.example.admissiontest.api.repository.ProductRepository
import com.example.admissiontest.model.Product
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel(){
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _status = MutableLiveData<ApiResponseStatus<Product>>()
    val status: LiveData<ApiResponseStatus<Product>>
        get() = _status

    private val productRepository = ProductRepository()

    fun downloadProductDetail(idProduct: Int){
        viewModelScope.launch{
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(productRepository.downloadProductDetail(idProduct))
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<Product>){
        if (apiResponseStatus is ApiResponseStatus.Success){
            _product.value = apiResponseStatus.data!!
        }

        _status.value = apiResponseStatus
    }
}