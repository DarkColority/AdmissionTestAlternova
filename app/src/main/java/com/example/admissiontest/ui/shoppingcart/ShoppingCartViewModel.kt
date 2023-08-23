package com.example.admissiontest.ui.shoppingcart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admissiontest.model.Product

class ShoppingCartViewModel: ViewModel() {

    private val _selectedProducts = MutableLiveData<List<Product>>()
    val selectedProducts: LiveData<List<Product>>
        get() = _selectedProducts

    init {
        _selectedProducts.value = mutableListOf()
    }

    fun addProduct(product: Product) {
        val currentList = _selectedProducts.value?.toMutableList() ?: mutableListOf()
        currentList.add(product)
        _selectedProducts.value = currentList
    }
}