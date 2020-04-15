package com.example.arteum.ui.search

import com.example.arteum.entity.searchresult.AlbumVoiceData
import com.example.arteum.entity.searchresult.ExhibitionData
import com.example.arteum.entity.searchresult.VoiceActorData

class SearchAdapterItem { // Adapter에는 모델과 별개로 어댑터에 맞는 뷰가 있는것이 맞다
    var header: String? = null
    var exhibitionData: ExhibitionData? = null
    var albumVoiceData: AlbumVoiceData? = null
    var voiceActorData: VoiceActorData? = null

    var viewType: Int? = null

    companion object {
        fun newHeader(title: String) =
            SearchAdapterItem().apply {
                viewType = SearchAdapter.HEADER
                header = title
            }

        fun newExhibitions(data: ExhibitionData) =
            SearchAdapterItem().apply {
                viewType = SearchAdapter.EXHIBITIONS
                exhibitionData = data
            }

        fun newAlbumVoice(data: AlbumVoiceData) =
            SearchAdapterItem().apply {
                viewType = SearchAdapter.ALBUM_VOICE
                albumVoiceData = data
            }

        fun newVoiceActors(data: VoiceActorData) =
            SearchAdapterItem().apply {
                viewType = SearchAdapter.VOICE_ACTORS
                voiceActorData = data
            }
    }


}