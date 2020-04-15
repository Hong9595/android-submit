package com.example.arteum.ui.main.exhibition

import com.example.arteum.entity.searchresult.ExhibitionData

interface ExhibitionContract {
    interface View{
        fun showExhibitionData(data: List<ExhibitionData>)
    }

    interface Presenter{
        fun getData()
    }
}