package com.example.arteum.data.api

import com.google.gson.JsonElement
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ArteumApi {

    @GET("api/search/exhibition")
    fun searchExhibition(
        @Query("keyword")   search: String?
    ): Single<JsonElement>


    @GET("api/search/album")
    fun searchAlbumVoice(
        @Query("keyword")   search: String
    ): Single<JsonElement>


    @GET("api/search/voiceActor")
    fun searchVoiceActor(
        @Query("keyword")   search: String
    ): Single<JsonElement>

    // detail Exhibition
    @GET("api/exhibition/{exhibition}")
    fun searchDetailExhibition(
        @Path("exhibition") exhibitionId: Int
    ): Single<JsonElement>

    // 전시 스크랩 리스트
    @GET("api/my/exhibition")
    fun getMyExhibition(): Single<JsonElement>

    // PUT api/exhibition/{exhibition}/scrap
    @PUT("api/exhibition/{exhibition}/scrap")
    fun scrapExhibition(
        @Path("exhibition") exhibitionId: Int
    ): Completable


}