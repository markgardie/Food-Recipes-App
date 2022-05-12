package com.markgardie.graduatework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.databinding.ProductsRowLayoutBinding
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.util.RecipesDiffUtil

class ProductsAdapter(): RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    private var products = emptyList<Product>()

    class MyViewHolder(private  val binding: ProductsRowLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductsRowLayoutBinding.inflate(layoutInflater, parent, false)
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

    fun setData(newData: ProductsList) {
        val recipesDiffUtil = RecipesDiffUtil(products, newData.products)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        products = newData.products

        diffUtilResult.dispatchUpdatesTo(this)

    }

}