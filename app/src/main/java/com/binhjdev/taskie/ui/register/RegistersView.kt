package com.binhjdev.taskie.ui.register

interface RegistersView {
    fun registerSuccessThenGoToLoginScreen(message : String)
    fun showError(error: Throwable)
}