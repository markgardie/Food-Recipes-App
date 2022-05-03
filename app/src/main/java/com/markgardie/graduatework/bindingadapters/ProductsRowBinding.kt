package com.markgardie.graduatework.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.markgardie.graduatework.R
import com.markgardie.graduatework.models.Result
import com.markgardie.graduatework.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup
import java.lang.Exception

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