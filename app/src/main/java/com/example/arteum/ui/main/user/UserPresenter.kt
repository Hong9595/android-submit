package com.example.arteum.ui.main.user

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.arteum.base.BasePresenter
import com.example.arteum.ui.login.LoginActivity
import com.kakao.network.ApiErrorCode
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.UnLinkResponseCallback

class UserPresenter : BasePresenter<UserContract.View>(), UserContract.Presenter {
    override fun signOut() {

        UserManagement.getInstance().requestUnlink(object: UnLinkResponseCallback(){
            // 회원탈퇴 실패시. 보통 네트워크 문제
            override fun onFailure(errorResult: ErrorResult?) {
                val result = errorResult?.errorCode
                if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                    view?.showToast("네트워크 연결이 불안정합니다. 다시 시도해 주세요.")
                } else {
                    view?.showToast("회원탈퇴에 실패했습니다. 다시 시도해 주세요.")
                }
            }

            // 로그인 세션 닫혀있을시. 로그인 자체가 안되어있는 경우
            override fun onSessionClosed(errorResult: ErrorResult?) {
                view?.showToast("로그인 세션이 닫혔습니다. 다시 로그인해 주세요.")
                view?.goToLoginActivity()
            }

            // 이건 보통 로그인쪽에서 내가 잘못 구현했을떄 호출 됨.
            override fun onNotSignedUp() {
                view?.showToast("가입되지 않은 계정입니다. 다시 로그인해 주세요.")
                view?.goToLoginActivity()
            }

            // 회원 탈퇴 성공한 경우
            override fun onSuccess(result: Long?) {
                view?.showToast("회원 탈퇴에 성공했습니다.")
                view?.goToLoginActivity()
            }
        })
    }

    override fun logOut() {
        UserManagement.getInstance().requestLogout(object: LogoutResponseCallback(){
            override fun onCompleteLogout() {
                view?.goToLoginActivity()
            }
        })
    }
}