package com.adicoding.foodmarket.base

interface BaseView {
    fun showLoading()
    fun dismissLoading()
    fun showError(message: String?)
    fun showMessage(message: String?)
}