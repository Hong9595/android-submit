package com.example.arteum.entity.searchresult

import com.google.gson.annotations.SerializedName


data class VoiceActorData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("main_image")
    val mainImage: String,
    @SerializedName("audios_count")
    val audioCount: Int
)