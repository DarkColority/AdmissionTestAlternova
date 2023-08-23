package com.example.admissiontest.ui.shoppingcart

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.admissiontest.R
import com.example.admissiontest.databinding.ProductListItemBinding
import com.example.admissiontest.databinding.ShoppingCartListItemBinding
import com.example.admissiontest.model.Product
import com.example.admissiontest.ui.productlist.ProductAdapter

class ShoppingCartAdapter:
    RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    private val shoppingList = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val binding = ShoppingCartListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ShoppingCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        val product = shoppingList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }
    fun setProducts(products: List<Product>) {
        shoppingList.clear()
        shoppingList.addAll(products)
        notifyDataSetChanged()
    }
    inner class ShoppingCartViewHolder(val binding: ShoppingCartListItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product) {
            binding.productImage.load(product.imageURL)
            binding.productNameText.text = product.name
            binding.productStockText.text = product.stock.toString()
            binding.productUnitPriceText.text = product.unitPrice.toString()
        }
    }
}






