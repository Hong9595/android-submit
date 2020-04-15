package com.example.arteum.data

import com.example.arteum.entity.searchresult.*
import io.reactivex.Completable
import io.reactivex.Single

interface SearchSource {
    fun searchExhibition(keyword: String?): Single<List<ExhibitionData>>
    fun searchAlbumVoice(keyword: String): Single<List<AlbumVoiceData>>
    fun searchVoiceActor(keyword: String): Single<List<VoiceActorData>>
    fun searchTotalData(keyword: String): Single<SearchResultData>

    fun searchDetailExhibition(exhibitionId: Int) : Single<ExhibitionDetailData>

    fun getMyExhibitionScrap(): Single<List<ExhibitionData>>

    fun scrapExhibition(exhibitionId: Int): Completable
}