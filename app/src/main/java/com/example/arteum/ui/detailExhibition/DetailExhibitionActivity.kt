package com.example.arteum.ui.detailExhibition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.arteum.R
import com.example.arteum.entity.searchresult.ExhibitionDetailData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_exhibition.*
import kotlinx.android.synthetic.main.nested_detail_exhibition.*

class DetailExhibitionActivity : AppCompatActivity(), DetailExhibitionContract.View, OnMapReadyCallback {
    private val detailPresenter = DetailExhibitionPresenter()
    private var gMap: GoogleMap? = null
    private var bookMarkFlag = false
    private var exhibitionId: Int = 0
    private var detailExhibitionData: ExhibitionDetailData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_exhibition)

        init()
        initListener()

        detailPresenter.getDetailData(exhibitionId)
    }
    private fun init() {
        detailPresenter.attachView(this)

        exhibitionId = intent.getIntExtra(EXHIBITION_ID, 1)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.detailGoogleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initListener() {
        // scrap
        detailExhibitScrap.setOnClickListener {
            if(bookMarkFlag) {
                detailExhibitScrap.setImageResource(R.drawable.ic_bookmark_border_white_24dp)
            } else {
                detailExhibitScrap.setImageResource(R.drawable.ic_bookmark_white_24dp)
            }
            bookMarkFlag = !bookMarkFlag

            // Server로 PUT
            detailPresenter.scrap(exhibitionId)
        }
    }
    override fun updateDetailData(detailData: Pair<ExhibitionDetailData, Boolean>) {
        detailExhibitionData = detailData.first

        detailData.first.run {
            Glide.with(this@DetailExhibitionActivity)
                .load(mainImage)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
                .into(detailExhibitionImage)

            detailExhibitionTitle.text = title
            exhibitionLocationContents.text = location
            exhibitionPeriodContents.text = getString(R.string.string_exhibition_date, startDate, endDate)
            exhibitionRestContents.text = holiday
            exhibitionRequestContents.text = contact
            exhibitionPriceContents.text = price

            detailIntroduceContents.text = content

            detailMapContents.text = address
            LatLng(lat, lng).let { location ->
                gMap?.run{
                    addMarker(MarkerOptions().position(location))
                    moveCamera(CameraUpdateFactory.newLatLngZoom(location,18.0f))
                }
            }
        }
        bookMarkFlag = detailData.second
        if(bookMarkFlag) detailExhibitScrap.setImageResource(R.drawable.ic_bookmark_white_24dp)
    }

    override fun onMapReady(p0: GoogleMap?) {
        gMap = p0
    }



    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.detachView()
    }

    companion object {
        const val EXHIBITION_ID = "exhibition_id"
    }
}
