package com.example.arteum.ui.main.user.scrap


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.arteum.R
import com.example.arteum.ui.search.SearchAdapter
import com.example.arteum.ui.search.SearchAdapterItem
import kotlinx.android.synthetic.main.fragment_scrap.*
import timber.log.Timber

class ScrapFragment : Fragment(), ScrapContract.View {
    private var title: String? = null
    private val scrapPresenter = ScrapPresenter()
    private val scrapAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(SCRAP_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scrap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        scrapPresenter.attachView(this)

        scrapRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = scrapAdapter
        }
        scrapPresenter.getMyScrap(title)
    }

    override fun updateView(items: MutableList<SearchAdapterItem>) {
        scrapAdapter.items = items
    }

    override fun onDestroy() {
        super.onDestroy()
        scrapPresenter.detachView()
    }

    companion object {
        const val SCRAP_TITLE = "scrap_title"
        @JvmStatic
        fun newInstance(title: String) =
            ScrapFragment().apply {
                arguments = Bundle().apply {
                    putString(SCRAP_TITLE, title)
                }
            }
    }
}
