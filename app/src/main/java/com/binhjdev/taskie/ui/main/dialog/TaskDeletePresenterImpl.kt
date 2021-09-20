package com.binhjdev.taskie.ui.main.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.utils.logCoroutine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TaskDeletePresenterImpl(private val taskRepository: TaskRepository) : ViewModel(), DeleteTaskPresenter {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private lateinit var deleteView: DeleteTaskView

    override fun setView(view: DeleteTaskView) {
        this.deleteView = view
    }

    override fun sendTaskIdDeleteData(taskId: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            logCoroutine("deleted task", coroutineContext)
            val result = runCatching { taskRepository.deletedTask(taskId) }
            result.onSuccess {
                deleteView.showMessageDeletedSuccess(it.message)
            }.onFailure {
                if (result.getOrNull()?.message == null) {
                    deleteView.showError(NullPointerException("No response!"))
                }
            }
        }
    }
}