package com.example.arteum.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arteum.R
import com.example.arteum.ext.hideKeyboard
import kotlinx.android.synthetic.main.activity_search.*
import timber.log.Timber


class SearchActivity : AppCompatActivity(), SearchContract.View {
    private val searchPresenter = SearchPresenter()
    private val searchResultAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        init()
        initListener()
        searchPresenter.getTotalData()
    }
    private fun init(){
        searchPresenter.attachView(this)

        setSupportActionBar(searchToolbar)
        supportActionBar?.title =""

        searchRecycler.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchResultAdapter
        }
    }

    private fun initListener() {
        // 키보드에서 검색 돋보기 눌린 경우 -> 검색 -> 데이터 발행
        searchEditText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchKeyword = searchEditText.text.toString()
                searchPresenter.emitSearchData(searchKeyword)
                true
            } else {
                false
            }
        }

        // 툴바에 검색 버튼 클릭 -> 데이터 발행
        searchImage.setOnClickListener {
            searchKeyword = searchEditText.text.toString()
            searchPresenter.emitSearchData(searchKeyword)
        }

        // 뒤로가기
        exitSearchBtn.setOnClickListener {
            finish()
        }
    }

    override fun showResult(items: MutableList<SearchAdapterItem>) {
        searchEditText.hideKeyboard()
        searchResultAdapter.items = items
    }

    override fun showNoDataText() {
        noResultTv.visibility = View.VISIBLE
        noResultTvSub.visibility = View.VISIBLE
        searchRecycler.visibility = View.INVISIBLE
    }

    override fun hideNoDataText() {
        noResultTv.visibility = View.INVISIBLE
        noResultTvSub.visibility = View.INVISIBLE
        searchRecycler.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        searchPresenter.detachView()
    }

    companion object{
        var searchKeyword: String = ""
    }
}
