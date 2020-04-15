package com.example.arteum.entity.searchresult

import com.google.gson.annotations.SerializedName

data class ExhibitionDetailData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("main_image")
    val mainImage: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("holiday")
    val holiday: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("link")
    val link: String ,
    @SerializedName("content")
    val content: String,
    @SerializedName("audios")
    val audios: List<AudioData>,
    @SerializedName("scrap")
    val scrap: Int,
    @SerializedName("blog")
    val blog: List<BlogData>
)

data class BlogData(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("bloggername")
    val bloggerName: String,
    @SerializedName("bloggerlink")
    val bloggerLink: String,
    @SerializedName("postdate")
    val postDate: String

)