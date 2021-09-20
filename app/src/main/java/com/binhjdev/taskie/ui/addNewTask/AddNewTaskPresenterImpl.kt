package com.binhjdev.taskie.ui.addNewTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binhjdev.taskie.data.model.request.AddTaskRequest
import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.utils.logCoroutine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AddNewTaskPresenterImpl(val taskRepository: TaskRepository) : AddNewTaskPresenter, ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private lateinit var addNewTaskView: AddNewTaskView

    override fun setView(view: AddNewTaskView) {
        this.addNewTaskView = view
    }

    override fun sendCreateTaskData(requestTask: AddTaskRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            logCoroutine("create task", coroutineContext)
            val result = runCatching { taskRepository.addTask(requestTask) }
            result.onSuccess {
                addNewTaskView.goToConfirmAddNewTaskScreen(it.title, it.content)
            }.onFailure {
                if (result.getOrNull() == null) {
                    addNewTaskView.showError(NullPointerException("No response!"))
                }
            }
        }
    }
}