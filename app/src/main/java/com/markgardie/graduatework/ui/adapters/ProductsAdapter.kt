package com.markgardie.graduatework.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.databinding.ProductsRowLayoutBinding
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.util.RecipesDiffUtil
import com.markgardie.graduatework.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.products_row_layout.view.*

class ProductsAdapter(
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
): RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    private var products = emptyList<Product>()
    private lateinit var rootView: View

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
        rootView = holder.itemView.rootView

        val currentProduct = products[position]
        holder.bind(currentProduct)


        val productEntity = ProductEntity(0, products[position])

        holder.itemView.add_to_cart_imageView.setOnClickListener {
            mainViewModel.addToCart(productEntity)
            showSnackBar("Product added")
        }
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

    private fun showSnackBar(message: String) {
        Snackbar.make(
            rootView,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
            .show()
    }

}