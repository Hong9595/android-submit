package com.example.arteum.ui.login

import android.content.Context
import com.example.arteum.base.BaseApplication
import com.kakao.auth.*


class KaKaoSDKAdapter : KakaoAdapter(){
    override fun getApplicationConfig(): IApplicationConfig {
        return IApplicationConfig {
            BaseApplication.instance.getGlobalApplicationContext()
        }
    }
}