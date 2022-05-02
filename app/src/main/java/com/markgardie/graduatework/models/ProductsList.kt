package com.markgardie.graduatework.models


import com.google.gson.annotations.SerializedName

data class ProductsList(
    @SerializedName("results")
    val products: List<Product>
)