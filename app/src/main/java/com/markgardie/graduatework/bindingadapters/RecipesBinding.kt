package com.markgardie.graduatework.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.markgardie.graduatework.data.database.entities.RecipesEntity
import com.markgardie.graduatework.models.FoodRecipe
import com.markgardie.graduatework.util.NetworkResult

class RecipesBinding {

    companion object {

        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility (
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {

            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            }
            else if (apiResponse is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            }
            else if (apiResponse is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }


        @BindingAdapter("readApiResponse2", "readDatabase2", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility (
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            }
            else if (apiResponse is NetworkResult.Loading) {
                textView.visibility = View.INVISIBLE
            }
            else if (apiResponse is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }
        }
    }

}