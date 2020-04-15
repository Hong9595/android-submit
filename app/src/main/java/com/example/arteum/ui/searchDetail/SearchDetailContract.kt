package com.example.arteum.ui.searchDetail

import com.example.arteum.ui.search.SearchAdapterItem

interface SearchDetailContract {

    interface View {
        fun showData(dataList: MutableList<SearchAdapterItem>)
        fun showToast(str: String)
    }

    interface Presenter {
        fun getDetailData(searchTitle: String?, searchKeyword: String?)
    }
}