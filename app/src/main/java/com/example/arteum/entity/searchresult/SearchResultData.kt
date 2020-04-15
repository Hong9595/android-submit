package com.example.arteum.entity.searchresult

data class SearchResultData(
    val exhibitions: List<ExhibitionData>,
    val albumVoices: List<AlbumVoiceData>,
    val voiceActors: List<VoiceActorData>
)
