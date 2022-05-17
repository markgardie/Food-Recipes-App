package com.markgardie.graduatework.ui.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.markgardie.graduatework.R
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.databinding.CartRowLayoutBinding
import com.markgardie.graduatework.ui.fragments.FavoriteRecipesFragmentDirections
import com.markgardie.graduatework.util.RecipesDiffUtil
import com.markgardie.graduatework.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.cart_row_layout.view.*
import kotlinx.android.synthetic.main.favorites_recipes_row_layout.view.*

class CartAdapter(
        private val requireActivity: FragmentActivity,
        private val mainViewModel: MainViewModel
): RecyclerView.Adapter<CartAdapter.MyViewHolder>(), ActionMode.Callback {

    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View

    private var products = emptyList<ProductEntity>()

    private var multiSelection = false

    private var selectedProducts = arrayListOf<ProductEntity>()
    private var myViewHolders = arrayListOf<MyViewHolder>()

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
        myViewHolders.add(holder)

        rootView = holder.itemView.rootView

        val currentProduct = products[position]
        holder.bind(currentProduct)

        holder.itemView.delete_product_imageView.setOnClickListener {
            mainViewModel.removeFromCart(currentProduct)
            showSnackBar("Product has been removed")
        }

        holder.itemView.cartRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentProduct)
            }
        }

        holder.itemView.cartRowLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentProduct)
                true
            } else {
                multiSelection = false
                false
            }
        }
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

    private fun applySelection(holder: MyViewHolder, currentProduct: ProductEntity) {
        if (selectedProducts.contains(currentProduct)) {
            selectedProducts.remove(currentProduct)
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyActionModeTitle()
        } else {
            selectedProducts.add(currentProduct)
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
            applyActionModeTitle()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.itemView.cartRowLayout.setBackgroundColor(
                ContextCompat.getColor(requireActivity, backgroundColor)
        )
        holder.itemView.cartCardView.strokeColor =
                ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionModeTitle(){
        when(selectedProducts.size) {
            0 -> {
                mActionMode.finish()
            }
            1 -> {
                mActionMode.title = "${selectedProducts.size} item selected"
            }
            else -> {
                mActionMode.title = "${selectedProducts.size} items selected"
            }
        }
    }

    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        mActionMode = actionMode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {

        if(menu?.itemId == R.id.delete_favorite_recipe_menu){
            selectedProducts.forEach {
                mainViewModel.removeFromCart(it)
            }
            showSnackBar("${selectedProducts.size} Product/s removed.")

            multiSelection = false
            selectedProducts.clear()
            actionMode?.finish()
        }

        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
        }
        multiSelection = false
        selectedProducts.clear()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int){
        requireActivity.window.statusBarColor =
                ContextCompat.getColor(requireActivity, color)
    }

    fun clearContextualActionMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
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