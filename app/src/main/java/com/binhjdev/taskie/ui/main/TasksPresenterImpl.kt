package com.binhjdev.taskie.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.utils.logCoroutine
import kotlinx.coroutines.launch

class TasksPresenterImpl(private val taskRepository: TaskRepository) : TasksPresenter, ViewModel() {

    private lateinit var tasksView: TasksView

    override fun setViews(tasksView: TasksView) {
        this.tasksView = tasksView
    }

    override fun getData() {
        viewModelScope.launch {
            logCoroutine("getData", coroutineContext)
            val result = runCatching { taskRepository.getTasks() }
            result.onSuccess { tasks ->
                tasksView.showTasks(tasks)
            }.onFailure {
                tasksView.showError(NullPointerException("No data available!"))
            }
        }
    }
}