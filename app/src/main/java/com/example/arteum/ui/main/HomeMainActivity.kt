package com.example.arteum.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.arteum.R
import com.example.arteum.ui.detailExhibition.DetailExhibitionActivity
import com.example.arteum.ui.main.exhibition.ExhibitionFragment
import com.example.arteum.ui.main.home.HomeFragment
import com.example.arteum.ui.main.nearExhibition.NearExhibitionFragment
import com.example.arteum.ui.main.user.UserFragment
import com.example.arteum.ui.main.voice.VoiceFragment
import com.example.arteum.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_home_main.*
import timber.log.Timber


class HomeMainActivity : AppCompatActivity() {
    private var profileUrl: String? = null
    private var name: String? = null
    private var email: String? = null

    private val homeFragment = HomeFragment()
    private val exhibitionFragment = ExhibitionFragment()
    private val nearExhibitionFragment = NearExhibitionFragment()
    private val voiceFragment = VoiceFragment()
    private lateinit var userFragment: UserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)

        init()
        initListener()
    }
    private fun init() {
        supportActionBar?.title = ""
        intent.let {
            profileUrl = it.getStringExtra("profileUrl")
            name = it.getStringExtra("name")
            email = it.getStringExtra("email")
        }

        userFragment = UserFragment.newInstance(profileUrl, name, email)

        // 시작 화면은 homeFragment
        val firstTransaction = supportFragmentManager.beginTransaction()
        firstTransaction.replace(R.id.mainFrameLayout, homeFragment).commit()
    }

    private fun initListener() {
        // 클릭시마다 fragment 전환 // bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val transaction = supportFragmentManager.beginTransaction()
            when(menuItem.itemId){
                R.id.navigation_home -> {
                    transaction.replace(R.id.mainFrameLayout, homeFragment).commit()
                    true
                }
                R.id.navigation_exhibitions -> {
                    transaction.replace(R.id.mainFrameLayout, exhibitionFragment).commit()
                    true
                }
                R.id.navigation_near_exhibitions -> {
                    transaction.replace(R.id.mainFrameLayout, nearExhibitionFragment).commit()
                    true
                }
                R.id.navigation_voice -> {
                    transaction.replace(R.id.mainFrameLayout, voiceFragment).commit()
                    true
                }
                R.id.navigation_user -> {
                    transaction.replace(R.id.mainFrameLayout, userFragment).commit()
                    true
                }
                else -> (true)
            }
        }
    }

}
