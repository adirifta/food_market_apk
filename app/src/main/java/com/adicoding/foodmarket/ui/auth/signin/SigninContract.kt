package com.adicoding.foodmarket.ui.auth.signin

import com.adicoding.foodmarket.base.BasePresenter
import com.adicoding.foodmarket.base.BaseView
import com.adicoding.foodmarket.model.response.login.LoginResponse

interface SigninContract {
    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter: SigninContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}