package com.example.arteum.entity.searchresult

import com.google.gson.annotations.SerializedName

data class ExhibitionData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("main_image")
    val mainImage: String,
    @SerializedName("location")
    val location: String
)