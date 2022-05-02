package com.markgardie.graduatework.models


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("s1350x1350")
    val s1350x1350: String,
    @SerializedName("s150x150")
    val s150x150: String,
    @SerializedName("s200x200")
    val s200x200: String,
    @SerializedName("s350x350")
    val s350x350: String
)