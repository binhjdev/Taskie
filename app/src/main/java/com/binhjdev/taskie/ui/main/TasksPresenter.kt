package com.binhjdev.taskie.ui.main

interface TasksPresenter {
    fun setViews(tasksView: TasksView)
    fun getData()
}