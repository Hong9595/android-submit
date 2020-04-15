package com.example.arteum.ui.init

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.arteum.R

class InitPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val imageList = listOf(R.drawable.tutorial_one, R.drawable.tutorial_two, R.drawable.tutorial_three, R.drawable.tutorial_four)

    override fun getItem(position: Int): Fragment  = InitFragment.newInstance(imageList[position])

    override fun getCount(): Int = imageList.size
}