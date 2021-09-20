package com.binhjdev.taskie.ui.main

import com.binhjdev.taskie.data.model.Task

interface TasksView {
    fun showTasks(tasks: List<Task>)
    fun showError(error : Throwable)
}