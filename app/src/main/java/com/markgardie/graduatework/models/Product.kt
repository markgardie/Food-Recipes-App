package com.markgardie.graduatework.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        @SerializedName("currency")
        val currency: String,
        @SerializedName("ean")
        val ean: String,
        @SerializedName("img")
        val img: Image,
        @SerializedName("price")
        val price: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("weight")
        val weight: Double
) : Parcelable