package com.markgardie.graduatework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.databinding.CartRowLayoutBinding
import com.markgardie.graduatework.databinding.ProductsRowLayoutBinding
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.util.RecipesDiffUtil

class CartAdapter(): RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var products = emptyList<Product>()

    class MyViewHolder(private val binding: CartRowLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CartAdapter.MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartRowLayoutBinding.inflate(layoutInflater, parent, false)
                return CartAdapter.MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        return CartAdapter.MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.bind(currentProduct)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(newData: ProductsList) {
        val recipesDiffUtil = RecipesDiffUtil(products, newData.products)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        products = newData.products

        diffUtilResult.dispatchUpdatesTo(this)

    }
}