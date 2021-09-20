package com.binhjdev.taskie.ui.login

import com.binhjdev.taskie.data.model.request.LoginUserDataRequest

interface LoginPresenter {
    fun setLoginView(loginView: LoginView)
    fun sendLoginUserData(sendLoginUserDataRequest: LoginUserDataRequest)
}