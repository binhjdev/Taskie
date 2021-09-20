package com.binhjdev.taskie.ui.main.dialog

interface DeleteTaskView {
    fun showMessageDeletedSuccess(message: String)
    fun showError(error : Throwable)
}
