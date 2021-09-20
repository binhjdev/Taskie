package com.binhjdev.taskie.ui.login

interface LoginView {
    fun loginSuccessThenGoMainScreen(token : String)
    fun showError(error :Throwable)
}