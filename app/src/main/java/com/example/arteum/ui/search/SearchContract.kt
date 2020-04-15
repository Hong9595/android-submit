package com.example.arteum.ui.search

interface SearchContract{

    interface View{
        fun showNoDataText()
        fun hideNoDataText()
        fun showResult(items: MutableList<SearchAdapterItem>)
    }

    interface Presenter{
        fun emitSearchData(keyword: String)
        fun getTotalData()
    }
}