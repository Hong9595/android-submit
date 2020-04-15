package com.example.arteum.ui.main.user.scrap

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ScrapPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val scrapList = listOf("전시", "보이스", "성우")

    override fun getItem(position: Int): Fragment = ScrapFragment.newInstance(scrapList[position])

    override fun getCount(): Int = scrapList.size

    override fun getPageTitle(position: Int): CharSequence? = scrapList[position]
}