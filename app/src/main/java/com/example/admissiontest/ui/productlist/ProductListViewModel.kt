package com.example.admissiontest.ui.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admissiontest.api.ApiResponseStatus
import com.example.admissiontest.api.repository.ProductRepository
import com.example.admissiontest.model.Product
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel(){
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private val _status = MutableLiveData<ApiResponseStatus<List<Product>>>()
    val status: LiveData<ApiResponseStatus<List<Product>>>
        get() = _status

    private val productRepository = ProductRepository()

    init {
        downloadProducts()
    }

    private fun downloadProducts(){
        viewModelScope.launch{
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(productRepository.downloadProducts())
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Product>>){
        if (apiResponseStatus is ApiResponseStatus.Success){
            _productList.value = apiResponseStatus.data!!
        }

        _status.value = apiResponseStatus
    }
}

