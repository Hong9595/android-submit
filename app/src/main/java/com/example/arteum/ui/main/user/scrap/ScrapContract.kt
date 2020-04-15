package com.example.arteum.ui.main.user.scrap

import com.example.arteum.ui.search.SearchAdapterItem

interface ScrapContract {
    interface View {
        fun updateView(items: MutableList<SearchAdapterItem>)
    }

    interface Presenter {
        fun getMyScrap(title: String?)
    }
}