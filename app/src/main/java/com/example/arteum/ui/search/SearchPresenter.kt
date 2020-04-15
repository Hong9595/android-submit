package com.example.arteum.ui.search

import com.example.arteum.base.BasePresenter
import com.example.arteum.data.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {
    private val searchRepo = SearchRepository()
    private var items = mutableListOf<SearchAdapterItem>()

    // 검색 단어가 있다면 true, 없다면 false 발행
    override fun emitSearchData(keyword: String) {
        if(keyword == ""){
            searchSubject.onNext(Pair(keyword,false))
        }
        else{
            searchSubject.onNext(Pair(keyword,true))
        }
    }


    override fun getTotalData() {
        compositeDisposable +=
            searchSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { it.second } // 검색 단어가 있는 경우에만 동작 수행
                .flatMap { searchRepo.searchTotalData(it.first).toObservable() }// 다른 observable로 바꿔줌
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    // searchResult는 전시, 보이스, 성우 데이터의 리스트가 merge된 데이터
                    // 3개씩만 받아서 리스트에 뿌려준다
                    onNext = { searchResult ->
                        items.clear()
                        if(searchResult.exhibitions.isNotEmpty()) {
                            items.add(SearchAdapterItem.newHeader("전시"))
                            searchResult.exhibitions
                                .take(3)
                                .map {
                                    items.add(SearchAdapterItem.newExhibitions(it))
                                }
                        }
                        if(searchResult.albumVoices.isNotEmpty()) {
                            items.add(SearchAdapterItem.newHeader("보이스"))
                            searchResult.albumVoices
                                .take(3)
                                .map {
                                    items.add(SearchAdapterItem.newAlbumVoice(it))
                                }
                        }
                        if(searchResult.voiceActors.isNotEmpty()) {
                            items.add(SearchAdapterItem.newHeader("성우"))
                            searchResult.voiceActors
                                .take(3)
                                .map {
                                    items.add(SearchAdapterItem.newVoiceActors(it))
                                }
                        }
                        // 3개 다 null이면 view에 "데이터 없음" 띄워주기 + 기존 data clear
                        // 1개라도 있으면 "데이터 없음" 지우기
                        if(searchResult.exhibitions.isEmpty() && searchResult.albumVoices.isEmpty() && searchResult.voiceActors.isEmpty()){
                            view?.showNoDataText()
                        } else {
                            view?.hideNoDataText()
                        }

                        view?.showResult(items)

                    },
                    onComplete = {},
                    onError = { Timber.e(it) }
                )
    }
    // 공통으로 쓸 경우가 있다면 EventBus // list 갱신
    companion object {
        val searchSubject = PublishSubject.create<Pair<String, Boolean>>()
    }

}