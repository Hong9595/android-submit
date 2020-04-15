package com.example.arteum.data.source

import com.example.arteum.data.ApiManager
import com.example.arteum.data.SearchSource
import com.example.arteum.entity.searchresult.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers


class SearchRemoteSource : SearchSource{
    private val arteumApi = ApiManager.arteumApi
    private val gson = ApiManager.gson

    override fun searchExhibition(keyword: String?): Single<List<ExhibitionData>> =
        arteumApi.searchExhibition(keyword)
            .map {
                it.asJsonObject.getAsJsonArray("data")
                    .map { data ->
                        gson.fromJson(data, ExhibitionData::class.java)
                    }
            }
            .subscribeOn(Schedulers.io())


    override fun searchAlbumVoice(keyword: String): Single<List<AlbumVoiceData>> =
        arteumApi.searchAlbumVoice(keyword)
            .map {
                it.asJsonObject.getAsJsonArray("data")
                    .map { data ->
                        gson.fromJson(data, AlbumVoiceData::class.java)
                    }
            }
            .subscribeOn(Schedulers.io())

    override fun searchVoiceActor(keyword: String): Single<List<VoiceActorData>> =
        arteumApi.searchVoiceActor(keyword)
            .map {
                it.asJsonObject.getAsJsonArray("data")
                    .map { data ->
                        gson.fromJson(data, VoiceActorData::class.java)
                    }
            }
            .subscribeOn(Schedulers.io())

    override fun searchTotalData(keyword: String): Single<SearchResultData> =
        Single.zip(
            searchExhibition(keyword),
            searchAlbumVoice(keyword),
            searchVoiceActor(keyword),
            Function3<List<ExhibitionData>, List<AlbumVoiceData>, List<VoiceActorData>, SearchResultData> { exhibitions, albums, voiceActors ->
                SearchResultData(exhibitions, albums, voiceActors)
            }
        )
            .subscribeOn(Schedulers.io())

    override fun searchDetailExhibition(exhibitionId: Int): Single<ExhibitionDetailData> =
        arteumApi.searchDetailExhibition(exhibitionId)
            .map {
                // getAsJsonArray -> "data" : [ {}, {}, ... ]
                // getAsJsonObject -> "data" : { title, location, ...}
                it.asJsonObject.getAsJsonObject("data").run {
                    gson.fromJson(this, ExhibitionDetailData::class.java)
                }
            }
            .subscribeOn(Schedulers.io())

    override fun getMyExhibitionScrap(): Single<List<ExhibitionData>> =
        arteumApi.getMyExhibition()
            .map {
                it.asJsonObject.getAsJsonArray("data")
                    .map { data ->
                        gson.fromJson(data, ExhibitionData::class.java)
                    }
            }
            .subscribeOn(Schedulers.io())

    override fun scrapExhibition(exhibitionId: Int): Completable =
        arteumApi.scrapExhibition(exhibitionId)
            .subscribeOn(Schedulers.io())

}