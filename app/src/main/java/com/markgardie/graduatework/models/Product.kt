package com.markgardie.graduatework.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Product(
        @SerializedName("ean")
        val ean: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("currency")
        val currency: String,
        @SerializedName("image")
        val image: String

) : Parcelable