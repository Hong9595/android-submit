package com.example.arteum.ui.main.user

interface UserContract {
    interface View {
        fun goToLoginActivity()
        fun showToast(str: String)
    }

    interface Presenter {
        fun signOut()
        fun logOut()
    }
}