package com.markgardie.graduatework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.databinding.CartRowLayoutBinding
import com.markgardie.graduatework.util.RecipesDiffUtil

class CartAdapter(): RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var products = emptyList<ProductEntity>()

    class MyViewHolder(private val binding: CartRowLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(productEntity: ProductEntity) {
            binding.productEntity = productEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.bind(currentProduct)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(newData: List<ProductEntity>) {
        val recipesDiffUtil = RecipesDiffUtil(products, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        products = newData

        diffUtilResult.dispatchUpdatesTo(this)

    }
}