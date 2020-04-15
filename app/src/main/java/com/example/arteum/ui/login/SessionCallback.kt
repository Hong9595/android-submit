package com.example.arteum.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.arteum.ui.init.InitAcitivity
import com.example.arteum.ui.main.user.UserFragment
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.ApiErrorCode
import com.kakao.usermgmt.StringSet.email
import com.kakao.usermgmt.StringSet.name
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import timber.log.Timber

// session 상태가 변경될 때 불리는 콜백
// 세션의 체크시 상태 변경에 따른 콜백. 세션이 오픈되었을 때, 세션이 닫혔을 때 세션 콜백을 넘기게 된다.
class SessionCallback(val context: Context) : ISessionCallback{
    // memory와 cache에 session 정보가 전혀 없는 상태. 일반적으로 로그인 버튼이 보이고 사용자가 클릭시 동의를 받아 access token 요청을 시도한다.
    override fun onSessionOpenFailed(exception: KakaoException?) {
        Timber.e("Session Callback: onSessionOpenFailed: ${exception?.message}")
    }

    // access token을 성공적으로 발급 받아 valid access token을 가지고 있는 상태. 일반적으로 로그인 후의 다음 activity로 이동한다.
    override fun onSessionOpened() {

        // 사용자 정보를 받아오는 abstract class
        UserManagement.getInstance().me(object: MeV2ResponseCallback(){
            // 가입이 안된 경우와 세션이 닫힌 경우를 제외한 다른 이유로 요청이 실패한 경우의 콜백
            override fun onFailure(errorResult: ErrorResult?) {
                if(errorResult?.errorCode == ApiErrorCode.CLIENT_ERROR_CODE){
                    Toast.makeText(context, "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"로그인 도중 오류가 발생했습니다: ", Toast.LENGTH_SHORT).show();
                }
                Timber.e("Session Call back :: onFailure ${errorResult?.errorMessage}")
            }

            // 세션이 닫혀 실패한 경우로 에러 결과. 재로그인 / 토큰발급이 필요합니다.
            // 로그인 도중 세션이 비정상적인 이유로 닫혔을 때 작동하는 함수.
            override fun onSessionClosed(errorResult: ErrorResult?) {
                Timber.e("Session Call back :: onSessionClosed ${errorResult?.errorMessage}")
            }
            // 성공시에 사용자 정보 받아옴
            // https://developers.kakao.com/docs/android/user-management#로그인  // 여기서 정보 확인 가능
            override fun onSuccess(result: MeV2Response?) {
                checkNotNull(result) { "session response null" }
                val intent = Intent(context, InitAcitivity::class.java).apply {
                    putExtra(UserFragment.PROFILE_URL,result.kakaoAccount.profile.profileImageUrl)
                    putExtra(UserFragment.NAME,result.kakaoAccount.profile.nickname)
                    putExtra(UserFragment.EMAIL,result.kakaoAccount.email)
                }
                context.startActivity(intent)
                (context as Activity).finish()
            }
        })
    }
}