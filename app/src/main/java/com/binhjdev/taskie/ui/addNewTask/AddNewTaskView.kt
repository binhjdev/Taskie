package com.binhjdev.taskie.ui.addNewTask

interface AddNewTaskView {
    fun showError(error : Throwable)
    fun goToConfirmAddNewTaskScreen(title: String, content: String)
}