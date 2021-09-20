package com.binhjdev.taskie.ui.profile

interface ProfileView {
    fun showProfile(email: String, name: String)
    fun showError(error: Throwable)
}