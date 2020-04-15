package com.example.arteum.ui.main.exhibition

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.example.arteum.R
import com.example.arteum.entity.searchresult.ExhibitionData
import com.example.arteum.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_exhibition.*

class ExhibitionFragment : Fragment(), ExhibitionContract.View {
    private val exhibitionPresenter = ExhibitionPresenter()
    private val exhibitionAdapter = ExhibitionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exhibition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()
        exhibitionPresenter.getData()
    }

    private fun init() {
        exhibitionPresenter.attachView(this)

        exhibitRecycler.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = exhibitionAdapter
        }
    }

    private fun initListener() {
        exhibitFilterImg.setOnClickListener {

        }

        exhibitSearchImg.setOnClickListener {
            startActivity(Intent(activity,SearchActivity::class.java))
        }

    }

    override fun showExhibitionData(data: List<ExhibitionData>) {
        exhibitionAdapter.items = data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exhibitionPresenter.detachView()
    }

}
