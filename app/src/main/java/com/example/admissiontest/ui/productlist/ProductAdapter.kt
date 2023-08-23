package com.example.admissiontest.ui.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.admissiontest.databinding.ProductListItemBinding
import com.example.admissiontest.model.Product


class ProductAdapter(
    private val listener: ProductAdapterListener
    ) : ListAdapter<Product,
        ProductAdapter.ProductViewHolder>(DiffCallback){

    companion object DiffCallback : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
    interface ProductAdapterListener {
        fun onAddToCartClicked(product: Product)
    }
    private var onItemClickListener: ((Product)-> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (Product)-> Unit){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(productViewHolder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        productViewHolder.bind(product)
    }

    inner class ProductViewHolder(val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product){
            var productCount = 0
            binding.productListItemLayout.setOnClickListener{
                onItemClickListener?.invoke(product)
            }
            binding.productImage.load(product.imageURL)
            binding.productNameText.text = product.name
            binding.productStockText.text = product.stock.toString()
            binding.productUnitPriceText.text = product.unitPrice.toString()
            binding.btnAddToCart.setOnClickListener {
                var newStock = product.stock
                if(product.stock > 0 && productCount <= newStock){
                    val productPosition = getItem(adapterPosition)
                    listener.onAddToCartClicked(productPosition)
                    newStock -= 1
                    productCount += 1
                    product.stock = newStock
                    Toast.makeText(it.context, "Added to cart!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(it.context, "This product is out of stock", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}