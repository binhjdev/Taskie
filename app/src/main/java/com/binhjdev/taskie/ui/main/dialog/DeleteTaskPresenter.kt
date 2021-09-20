package com.binhjdev.taskie.ui.main.dialog

interface DeleteTaskPresenter {
    fun sendTaskIdDeleteData(taskId: String)
    fun setView(view: DeleteTaskView)
}