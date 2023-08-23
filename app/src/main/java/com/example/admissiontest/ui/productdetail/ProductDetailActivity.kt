package com.example.admissiontest.ui.productdetail

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import coil.load
import com.example.admissiontest.R
import com.example.admissiontest.databinding.ActivityProductDetailBinding
import com.example.admissiontest.model.Product
import com.example.admissiontest.ui.shoppingcart.ShoppingCartActivity
import com.example.admissiontest.ui.shoppingcart.ShoppingCartViewModel

class ProductDetailActivity : AppCompatActivity() {

    private val productDetailViewModel: ProductDetailViewModel by viewModels()
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
    companion object{
        const val PRODUCT_KEY = "product"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingWheel = binding.loadingWheel
        loadingWheel.visibility = View.VISIBLE
        val product = intent?.extras?.getParcelable<Product>(PRODUCT_KEY)
        if(product == null){
            Toast.makeText(this, R.string.there_was_an_error, Toast.LENGTH_SHORT).show()
            finish()
            return
        }else{
            loadingWheel.visibility = View.GONE
        }
        val productDetail = productDetailViewModel.downloadProductDetail(product.id)

        if (productDetail==null){
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "onCreate: $productDetail")
        }
        var productCount = 0
        binding.productUnitPriceText.text = getString(R.string.product_price_format)
        binding.productStockText.text = getString(R.string.product_stock_format)
        binding.product = product
        binding.productImage.load(product.imageURL)
        //binding.productDescriptionText.text = description
        binding.btnAddToCart.setOnClickListener {
            var newStock = product.stock
            if(product.stock > 0 && productCount <= newStock){
                newStock -= 1
                productCount += 1
                product.stock = newStock
                addToCart(product)
                Toast.makeText(it.context, "Added to cart!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(it.context, "This product is out of stock!", Toast.LENGTH_SHORT).show()
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
            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }
    }
    private fun addToCart(product: Product) {
        shoppingCartViewModel.addProduct(product)
    }

}