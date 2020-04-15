package com.example.arteum.data.repository

import com.example.arteum.data.SearchSource
import com.example.arteum.data.source.SearchRemoteSource
import com.example.arteum.entity.searchresult.*
import io.reactivex.Completable
import io.reactivex.Single

class SearchRepository: SearchSource {
    private val searchRemoteSource = SearchRemoteSource()

    override fun searchExhibition(keyword: String?): Single<List<ExhibitionData>> =
        searchRemoteSource.searchExhibition(keyword)

    override fun searchAlbumVoice(keyword: String): Single<List<AlbumVoiceData>> =
        searchRemoteSource.searchAlbumVoice(keyword)

    override fun searchVoiceActor(keyword: String): Single<List<VoiceActorData>> =
        searchRemoteSource.searchVoiceActor(keyword)

    override fun searchTotalData(keyword: String): Single<SearchResultData> =
        searchRemoteSource.searchTotalData(keyword)


    override fun searchDetailExhibition(exhibitionId: Int): Single<ExhibitionDetailData> =
        searchRemoteSource.searchDetailExhibition(exhibitionId)

    override fun getMyExhibitionScrap(): Single<List<ExhibitionData>> =
        searchRemoteSource.getMyExhibitionScrap()

    override fun scrapExhibition(exhibitionId: Int): Completable =
        searchRemoteSource.scrapExhibition(exhibitionId)
}