package com.example.arteum.ui.searchDetail

import com.example.arteum.base.BasePresenter
import com.example.arteum.data.repository.SearchRepository
import com.example.arteum.ui.search.SearchAdapterItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class SearchDetailPresenter: SearchDetailContract.Presenter, BasePresenter<SearchDetailContract.View>() {
    private val searchRepo = SearchRepository()
    private var items = mutableListOf<SearchAdapterItem>()
    override fun getDetailData(searchTitle:String?, searchKeyword: String?) {
        if(searchKeyword != null){
            when(searchTitle) {
                SearchDetailActivity.DETAIL_EXHIBITIONS -> { // 전시
                    compositeDisposable += searchRepo
                        .searchExhibition(searchKeyword)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy (
                            onSuccess = {
                                it.map { exhibitionData ->
                                    items.add(SearchAdapterItem.newExhibitions(exhibitionData))
                                }
                                view?.showData(items)
                            },
                            onError = {}
                        )
                }
                SearchDetailActivity.DETAIL_VOICE -> {// 보이스
                    compositeDisposable += searchRepo
                        .searchAlbumVoice(searchKeyword)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy (
                            onSuccess = {
                                it.map { albumVoiceData ->
                                    items.add(SearchAdapterItem.newAlbumVoice(albumVoiceData))
                                }
                                view?.showData(items)
                            },
                            onError = {}
                        )
                }
                SearchDetailActivity.DETAIL_VOICE_ACTORS -> {// 성우
                    compositeDisposable += searchRepo
                        .searchVoiceActor(searchKeyword)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy (
                            onSuccess = {
                                it.map { voiceActorData ->
                                    items.add(SearchAdapterItem.newVoiceActors(voiceActorData))
                                }
                                view?.showData(items)
                            },
                            onError = {}
                        )
                }
                else -> { view?.showToast("다시 검색해주세요") } // searchTitle가 다른게 온 경우 -> 그냥 빈 리스트
            }
        } else { // searchKeyword가 null
            view?.showToast("다시 검색해주세요")
        }

    }
}