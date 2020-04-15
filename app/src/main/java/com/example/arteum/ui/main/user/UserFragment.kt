package com.example.arteum.ui.main.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide

import com.example.arteum.R
import com.example.arteum.ui.login.LoginActivity
import com.example.arteum.ui.main.HomeMainActivity
import com.example.arteum.ui.main.user.scrap.MyScrapActivity
import com.kakao.network.ApiErrorCode
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.UnLinkResponseCallback
import kotlinx.android.synthetic.main.fragment_user.*
import timber.log.Timber

class UserFragment : Fragment(), UserContract.View {

    private var profileUrl: String? = null
    private var name: String? = null
    private var email: String? = null

    private val presenter = UserPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            profileUrl = it.getString(PROFILE_URL)
            name = it.getString(NAME)
            email = it.getString(EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()
    }
    private fun init(){
        presenter.attachView(this)

        if(profileUrl != null){
            Glide.with(this)
                .load(profileUrl)
                .into(userProfileImage)
        }
        userName.text = name
        userEmail.text = email

    }
    private fun initListener() {
        myScrapText.setOnClickListener {
            startActivity(Intent(activity, MyScrapActivity::class.java))
        }

        logoutBtn.setOnClickListener {
            presenter.logOut()
        }

        signOutBtn.setOnClickListener {
            presenter.signOut()
        }
    }

    override fun goToLoginActivity() {
        startActivity(Intent(activity, LoginActivity::class.java))
        activity!!.finishAffinity() // 현재 task에서 같은 affinity가진 애들 모두 제거.
    }

    override fun showToast(str: String) {
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    companion object {
        const val PROFILE_URL = "profile_url"
        const val NAME = "name"
        const val EMAIL = "email"
        @JvmStatic
        fun newInstance(profileUrl: String?, name: String?, email: String?) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(PROFILE_URL, profileUrl)
                    putString(NAME, name)
                    putString(EMAIL, email)
                }
            }
    }
}
