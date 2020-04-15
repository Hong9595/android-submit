package com.example.arteum.ui.detailExhibition

import com.example.arteum.base.BasePresenter
import com.example.arteum.data.repository.SearchRepository
import com.example.arteum.entity.searchresult.ExhibitionDetailData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class DetailExhibitionPresenter : DetailExhibitionContract.Presenter, BasePresenter<DetailExhibitionContract.View>() {
    private var scrapFlag = false
    private val searchRepo = SearchRepository()

    override fun getDetailData(exhibitionId: Int) {
        var compData: ExhibitionDetailData? = null
        compositeDisposable += searchRepo.searchDetailExhibition(exhibitionId)
            .flatMap { detailData ->
                compData = detailData
                searchRepo.getMyExhibitionScrap()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { exhibitions ->
                    exhibitions.map { exhibition ->
                        if(exhibition.id == exhibitionId){
                            scrapFlag = true
                        }
                    }
                    compData?.let {
                        view?.updateDetailData(Pair(it,scrapFlag))
                    }

                },
                onError = {}
            )
    }

    override fun scrap(exhibitionId: Int) {
        compositeDisposable += searchRepo.scrapExhibition(exhibitionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {  },
                onError = { Timber.e(it) }
            )

    }

}