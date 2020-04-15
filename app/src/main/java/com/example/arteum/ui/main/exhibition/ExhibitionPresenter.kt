package com.example.arteum.ui.main.exhibition

import com.example.arteum.base.BasePresenter
import com.example.arteum.data.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ExhibitionPresenter : ExhibitionContract.Presenter, BasePresenter<ExhibitionContract.View>() {
    private val searchRepo = SearchRepository()
    override fun getData() {
        compositeDisposable +=
            searchRepo.searchExhibition(null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onSuccess = { view?.showExhibitionData(it) },
                    onError = { Timber.e(it) }
                )
    }
}