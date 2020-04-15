package com.example.arteum.entity.searchresult

import com.google.gson.annotations.SerializedName

data class AlbumVoiceData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("main_image")
    val mainImage: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("audio")
    val audio: List<AudioData>
)

data class AudioData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("media")
    val media: String,
    @SerializedName("voice_actor")
    val voiceActor: VoiceActorData?
)
