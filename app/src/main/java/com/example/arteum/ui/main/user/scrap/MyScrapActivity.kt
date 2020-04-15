package com.example.arteum.ui.main.user.scrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arteum.R
import kotlinx.android.synthetic.main.activity_my_scrap.*

class MyScrapActivity : AppCompatActivity() {
    private val scrapPagerAdater = ScrapPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_scrap)

        init()
        initListener()
    }
    private fun init() {
        scrapViewPager.adapter = scrapPagerAdater
        scrapTab.setupWithViewPager(scrapViewPager)
    }

    private fun initListener() {
        scrapBackIcon.setOnClickListener {
            finish()
        }
    }
}
