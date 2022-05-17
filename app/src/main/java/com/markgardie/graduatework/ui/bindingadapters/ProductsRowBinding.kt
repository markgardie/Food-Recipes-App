package com.markgardie.graduatework.ui.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.markgardie.graduatework.R

class ProductsRowBinding {

    companion object {


        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }


        @BindingAdapter("setPrice")
        @JvmStatic
        fun setPrice(textView: TextView, price: Int) {
            val formatPrice = price / 100.toDouble()
            textView.text = formatPrice.toString()
        }

        @BindingAdapter("setWeight")
        @JvmStatic
        fun setWeight(textView: TextView, weight: Double) {
            textView.text = weight.toString()
        }

    }
}