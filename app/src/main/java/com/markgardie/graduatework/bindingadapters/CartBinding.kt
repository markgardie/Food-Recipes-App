package com.markgardie.graduatework.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.markgardie.graduatework.data.database.entities.ProductEntity

class CartBinding {

    companion object {

        @BindingAdapter("setTotalPrice")
        @JvmStatic
        fun setTotalPrice(textView: TextView, productEntity: List<ProductEntity>) {
            var totalPrice = 0.0

            if (productEntity.isNullOrEmpty()) {
                for (products in productEntity) {
                    totalPrice += products.product.price / 100 .toDouble()
                }
            }

            textView.text = totalPrice.toString()
        }
    }
}