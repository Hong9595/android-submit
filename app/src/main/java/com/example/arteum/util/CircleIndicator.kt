package com.example.arteum.util

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout

class CircleIndicator: LinearLayout{
    private var indicatorContext: Context? = null

    private var defaultCircle: Int = 0
    private var selectCircle: Int = 0

    private var imageDot: MutableList<ImageView> = mutableListOf()

    // imageview.setpadding에 pixel밖에 못 넣어주므로 사용하는 변수
    // applyDimension : dp to pixel 단위 바꿔주는 함수
    // displayMetrics supplies display density and scaling information. // density: mdpi를 기준으로 한 배율. 스케일링시 곱해지는 값
    // 3dp를 pixel로 바꾼다 // px = dp * (dpi / 160)
    private val temp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)

    constructor(context: Context): super(context) {
        indicatorContext = context
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        indicatorContext = context
    }

    // indicator 생성
    fun createDotPanel(count: Int, defaultCircle: Int, selectCircle: Int, position: Int) {
        this.removeAllViews()
        this.defaultCircle = defaultCircle
        this.selectCircle = selectCircle

        for (i in 0 until count) {
            // ImageView에 대한 설정 관리
            imageDot.add(ImageView(indicatorContext).apply { setPadding(temp.toInt(), 0, temp.toInt(), 0) })
            this.addView(imageDot[i])
        }
        //인덱스 선택
        selectDot(position)
    }

    // 선택된 페이지 표시
    fun selectDot(position: Int) {
        for (i in imageDot.indices) {
            if (i == position) {
                imageDot[i].setImageResource(selectCircle)
            } else {
                imageDot[i].setImageResource(defaultCircle)
            }
        }

    }

}