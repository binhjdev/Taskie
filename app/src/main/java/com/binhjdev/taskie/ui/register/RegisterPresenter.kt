package com.binhjdev.taskie.ui.register

import com.binhjdev.taskie.data.model.request.RegisterUserDataRequest

interface RegisterPresenter {
    fun setViews(registersView: RegistersView)
    fun sendRegisterUserData(sendRequestRegister: RegisterUserDataRequest)
}