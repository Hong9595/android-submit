package com.example.arteum.base

import android.app.Application
import android.util.Log
import com.example.arteum.BuildConfig
import com.example.arteum.ui.login.KaKaoSDKAdapter
import com.facebook.stetho.Stetho
import com.kakao.auth.KakaoSDK
import timber.log.Timber

class BaseApplication : Application(){

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        Stetho.initializeWithDefaults(this)

        // KakaoSDK 연동
        instance = this
        KakaoSDK.init(KaKaoSDKAdapter())

        // mode에 따른 Timber tree plant
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    fun getGlobalApplicationContext(): BaseApplication {
        return instance
    }


    private class CrashReportingTree : Timber.Tree(){
        // error handle logic
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }


        }
//        override fun d(t: Throwable?) {
//            t?.printStackTrace()
//        }

    }
}