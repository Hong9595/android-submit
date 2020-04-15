package com.example.arteum.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.arteum.ui.init.InitAcitivity
import com.example.arteum.R
import com.example.arteum.ui.detailExhibition.DetailExhibitionActivity
import com.example.arteum.ui.login.LoginActivity
import com.example.arteum.ui.login.SessionCallback
import com.kakao.auth.Session
import timber.log.Timber

class SpalshActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 1500 //1.5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        init()
    }

    private fun init() {
        // SPLASH_DELAY 이후에 message queue에 msg 전달
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },SPLASH_DELAY)
    }

}
