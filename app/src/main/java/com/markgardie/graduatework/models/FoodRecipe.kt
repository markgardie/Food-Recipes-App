package com.markgardie.graduatework.models


import com.google.gson.annotations.SerializedName
import com.markgardie.graduatework.models.Result

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)