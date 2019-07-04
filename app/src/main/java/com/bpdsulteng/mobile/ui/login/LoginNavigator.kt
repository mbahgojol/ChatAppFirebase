package com.bpdsulteng.mobile.ui.login

interface LoginNavigator {
    fun onSuccessLogin()
    fun onErorrLogin(msg: String)
}