package com.example.arteum.ui.init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.arteum.R
import com.example.arteum.ui.main.HomeMainActivity
import com.example.arteum.ui.main.user.UserFragment
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthService
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.StringSet.email
import com.kakao.usermgmt.StringSet.name
import kotlinx.android.synthetic.main.activity_init_pager.*
import timber.log.Timber

class InitAcitivity : AppCompatActivity() {
    private val pagerAdpater = InitPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_pager)
        init()
        initListener()
    }

    private fun init() {
        initViewPager.adapter = pagerAdpater
        initIndicator.createDotPanel(pageNum,R.drawable.indicator_dot_off,R.drawable.indicator_dot_on,0)
    }

    private fun initListener() {
        nextBtn.setOnClickListener {
            val homeIntent = Intent(this, HomeMainActivity::class.java).apply {
                intent.let {
                    putExtra(UserFragment.PROFILE_URL, it.getStringExtra(UserFragment.PROFILE_URL))
                    putExtra(UserFragment.NAME, it.getStringExtra(UserFragment.NAME))
                    putExtra(UserFragment.EMAIL, it.getStringExtra(UserFragment.EMAIL))
                }
            }
            startActivity(homeIntent)
        }

        initViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                initIndicator.selectDot(position)
                if(position == pagerAdpater.count - 1){
                    nextBtn.visibility = View.VISIBLE
                } else {
                    nextBtn.visibility = View.INVISIBLE
                }
            }
        })
    }

    companion object{
        private const val pageNum = 4
    }
}
