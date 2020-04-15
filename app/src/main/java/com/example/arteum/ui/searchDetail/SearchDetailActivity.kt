package com.example.arteum.ui.searchDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arteum.R
import com.example.arteum.ui.search.SearchAdapter
import com.example.arteum.ui.search.SearchAdapterItem
import kotlinx.android.synthetic.main.activity_search_detail.*
import retrofit2.http.HEAD
import timber.log.Timber

class SearchDetailActivity : AppCompatActivity(), SearchDetailContract.View {
    private val searchDetailPresenter = SearchDetailPresenter()
    private val searchDetailAdapter = SearchAdapter()
    private var searchTitle: String? = null
    private var searchKeyword: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)

        init()
        initListener()

        // 검색 키워드를 전달
        searchDetailPresenter.getDetailData(searchTitle, searchKeyword)
    }

    private fun init() {
        searchDetailPresenter.attachView(this)

        intent.run {
            searchTitle = getStringExtra(HEADER_TITLE)
            searchKeyword = getStringExtra(SEARCH_KEYWORD)
        }

        // toolbar 등록
        setSupportActionBar(searchDetailToolber)
        supportActionBar?.title = ""
        searchDetailTitle.text = getString(R.string.searchDetatilTitle, searchTitle)

        searchDetailRecycler.apply {
            layoutManager = LinearLayoutManager(this@SearchDetailActivity)
            adapter = searchDetailAdapter
        }
    }
    private fun initListener() {
        searchDetailBackIcon.setOnClickListener {
            finish()
        }
    }

    override fun showData(dataList: MutableList<SearchAdapterItem>) {
        searchDetailAdapter.items = dataList
    }

    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchDetailPresenter.detachView()
    }

    companion object {
        const val HEADER_TITLE = "header_title"
        const val SEARCH_KEYWORD = "search_keyword"
        const val DETAIL_EXHIBITIONS = "전시"
        const val DETAIL_VOICE = "보이스"
        const val DETAIL_VOICE_ACTORS = "성우"
    }
}
