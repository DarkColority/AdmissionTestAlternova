package com.example.admissiontest.ui.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admissiontest.databinding.ActivityShoppingCartBinding
import com.example.admissiontest.model.Product

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var shoppingList: List<Product>
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shoppingList = intent?.extras?.getParcelableArrayList<Product>("shoppingList") ?: emptyList()

        val recycler = binding.shoppingCartRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = ShoppingCartAdapter()
        recycler.adapter = adapter

        adapter.setProducts(shoppingList)
    }
}