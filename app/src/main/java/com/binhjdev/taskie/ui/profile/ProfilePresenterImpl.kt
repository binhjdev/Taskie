package com.binhjdev.taskie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.utils.logCoroutine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ProfilePresenterImpl(private val taskRepository: TaskRepository) : ViewModel(), ProfilePresenter {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private lateinit var profileView: ProfileView

    override fun setView(view: ProfileView) {
        this.profileView = view
    }

    override fun getData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            logCoroutine("get user data", coroutineContext)
            val result = runCatching { taskRepository.getUserProfile() }
            result.onSuccess {
                profileView.showProfile(it.name, it.email)
            }.onFailure {
                if (result.getOrNull() == null) {
                    profileView.showError(NullPointerException("No data!"))
                }
            }
        }
    }
}