package com.adicoding.foodmarket.ui.home

import com.adicoding.foodmarket.base.BasePresenter
import com.adicoding.foodmarket.base.BaseView
import com.adicoding.foodmarket.model.response.home.HomeResponse
import com.adicoding.foodmarket.model.response.login.LoginResponse

interface HomeContract {
    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter: HomeContract, BasePresenter {
        fun getHome()
    }
}