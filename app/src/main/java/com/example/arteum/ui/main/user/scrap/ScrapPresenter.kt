package com.example.arteum.ui.main.user.scrap

import com.example.arteum.base.BasePresenter
import com.example.arteum.data.repository.SearchRepository
import com.example.arteum.ui.search.SearchAdapter
import com.example.arteum.ui.search.SearchAdapterItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ScrapPresenter : BasePresenter<ScrapContract.View>(), ScrapContract.Presenter {
    private val scrapRepo = SearchRepository()
    private var scrapItems = mutableListOf<SearchAdapterItem>()

    override fun getMyScrap(title: String?) {
        when(title) {
            "전시" -> {
                scrapRepo.getMyExhibitionScrap()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy (
                        onSuccess = {
                            scrapItems.clear()
                            it.map {  exhibitionData ->
                                scrapItems.add(SearchAdapterItem.newExhibitions(exhibitionData))
                            }
                            view?.updateView(scrapItems)
                        },
                        onError = { Timber.e(it) }
                    )
            }
            "보이스" -> {}
            "성우" -> {}
        }
    }
}