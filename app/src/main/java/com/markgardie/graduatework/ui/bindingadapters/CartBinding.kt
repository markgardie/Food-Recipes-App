package com.markgardie.graduatework.ui.bindingadapters

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.data.database.entities.FavoritesEntity
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.ui.adapters.CartAdapter
import com.markgardie.graduatework.ui.adapters.FavoriteRecipesAdapter

class CartBinding {

    companion object {

        @BindingAdapter("setTotalPrice")
        @JvmStatic
        fun setTotalPrice(textView: TextView, productEntity: List<ProductEntity>?) {
            var totalPrice = 0.0

            if (productEntity != null) {
                for (products in productEntity) {
                    totalPrice += products.product.price / 100 .toDouble()
                }
            }

            textView.text = totalPrice.toString()
        }

        @BindingAdapter("cartViewVisibility", "cartSetData", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            productEntity: List<ProductEntity>?,
            mAdapter: CartAdapter?
        ) {
            if (productEntity.isNullOrEmpty()) {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.VISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            } else {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        mAdapter?.setData(productEntity)
                    }
                }
            }
        }

        @BindingAdapter("buttonVisibility")
        @JvmStatic
        fun buttonVisibility(button: Button, productEntity: List<ProductEntity>?) {
            if (productEntity.isNullOrEmpty()) {
                button.visibility = Button.INVISIBLE
            }
            else {
                button.visibility = Button.VISIBLE
            }

        }
    }


}