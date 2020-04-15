package com.example.arteum.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arteum.ui.init.InitAcitivity
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber


class LoginActivity : AppCompatActivity() {
    private var callback: SessionCallback = SessionCallback(this)
    private val session = Session.getCurrentSession()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.arteum.R.layout.activity_login)

        init()
        initListener()
    }
    private fun init() {
        // 세션 콜백 추가
        session.addCallback(callback)

        // 세션 체크하여 자동 로그인
        session.checkAndImplicitOpen()
    }

    private fun initListener() {
        loginKakaoBtn.setOnClickListener { view ->
            // open : 세션 오픈을 진행한다.
            session.open(AuthType.KAKAO_TALK, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        session.removeCallback(callback)
    }

    // 로그인 activity를 이용하여 sdk에서 필요로 하는 activity(카카오자체 로그인 액티비티)를 띄우기 때문에 해당 결과를 세션에도 넘겨줘서 처리할 수 있도록 Session#handleActivityResult를 호출
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(session.handleActivityResult(requestCode, resultCode, data)){
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
