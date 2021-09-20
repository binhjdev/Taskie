package com.binhjdev.taskie.ui.addNewTask

import com.binhjdev.taskie.data.model.request.AddTaskRequest

interface AddNewTaskPresenter {
    fun setView(view: AddNewTaskView)
    fun sendCreateTaskData(requestTask: AddTaskRequest)
}