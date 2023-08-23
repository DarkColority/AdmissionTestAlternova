package com.example.admissiontest.ui.productlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admissiontest.R
import com.example.admissiontest.api.ApiResponseStatus
import com.example.admissiontest.ui.shoppingcart.ShoppingCartActivity
import com.example.admissiontest.databinding.ActivityProductListBinding
import com.example.admissiontest.model.Product
import com.example.admissiontest.ui.productdetail.ProductDetailActivity
import com.example.admissiontest.ui.productdetail.ProductDetailActivity.Companion.PRODUCT_KEY
import com.example.admissiontest.ui.shoppingcart.ShoppingCartViewModel

class ProductListActivity : AppCompatActivity(), ProductAdapter.ProductAdapterListener {

    private val productListViewModel: ProductListViewModel by viewModels()
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingWheel = binding.loadingWheel
        val recycler = binding.productRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = ProductAdapter(this)

        adapter.setOnItemClickListener {
            openProductDetail(it)
        }
        recycler.adapter = adapter

        productListViewModel.productList.observe(this){
                productList -> adapter.submitList(productList)
        }
        productListViewModel.status.observe(this){
                status ->
            when(status){
                is ApiResponseStatus.Error -> {

                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, status.messageId, Toast.LENGTH_SHORT).show()

                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val btnShoppingCart = binding.btnShoppingCart
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Shopping list"
            setDisplayHomeAsUpEnabled(false)
        }
        toolbar.inflateMenu(R.menu.menu_main)
        btnShoppingCart.setOnClickListener {
            val selectedProducts = shoppingCartViewModel.selectedProducts.value ?: emptyList()
            val intent = Intent(this, ShoppingCartActivity::class.java)
            intent.putExtra("shoppingList", ArrayList(selectedProducts))
            startActivity(intent)
        }
    }
    private fun openProductDetail(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(PRODUCT_KEY, product)
        startActivity(intent)
    }
    override fun onAddToCartClicked(product: Product) {
        shoppingCartViewModel.addProduct(product)

    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false },
            2000)
    }
}