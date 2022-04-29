package com.markgardie.graduatework.models

import com.google.gson.annotations.SerializedName

data class ProductsList(
        @SerializedName("products_list")
        val productsList: List<Product>
)