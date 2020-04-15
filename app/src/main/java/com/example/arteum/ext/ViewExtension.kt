package com.example.arteum.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

// Activity의 extension으로 정의할 경우 Dialog Fragment등에서 사용 불가.
fun View.hideKeyboard() {
    // 해당 뷰의 keyboard를 얻기 위한 api
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}