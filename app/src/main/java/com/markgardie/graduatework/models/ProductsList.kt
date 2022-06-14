package com.markgardie.graduatework.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsList(
    @SerializedName("results")
    val products: List<Product>
): Parcelable