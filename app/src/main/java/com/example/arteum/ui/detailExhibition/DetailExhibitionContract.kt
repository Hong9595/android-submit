package com.example.arteum.ui.detailExhibition

import com.example.arteum.entity.searchresult.ExhibitionDetailData

interface DetailExhibitionContract {
    interface View {
        fun updateDetailData(detailData: Pair<ExhibitionDetailData, Boolean>)
    }

    interface Presenter {
        fun getDetailData(exhibitionId: Int)
        fun scrap(exhibitionId: Int)
    }
}