package com.markgardie.graduatework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.data.database.entities.FavoritesEntity
import com.markgardie.graduatework.databinding.FavoritesRecipesRowLayoutBinding
import com.markgardie.graduatework.databinding.ProductsRowLayoutBinding
import com.markgardie.graduatework.models.FoodRecipe
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.util.RecipesDiffUtil
import com.markgardie.graduatework.viewmodels.MainViewModel

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

    fun setData(newData: List<Product>) {
        val recipesDiffUtil = RecipesDiffUtil(products, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        products = newData

        diffUtilResult.dispatchUpdatesTo(this)

    }

}