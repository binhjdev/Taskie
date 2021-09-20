package com.binhjdev.taskie.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binhjdev.taskie.data.model.request.RegisterUserDataRequest
import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.utils.logCoroutine
import kotlinx.coroutines.*

class RegisterPresenterImpl(private val taskRepository: TaskRepository) : ViewModel(),
    RegisterPresenter {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        throwable.printStackTrace()
    }
    private lateinit var registersView: RegistersView

    override fun setViews(registersView: RegistersView) {
        this.registersView = registersView
    }

    override fun sendRegisterUserData(registerUserData: RegisterUserDataRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            logCoroutine("register user", coroutineContext)
            val result = runCatching { taskRepository.registerUser(registerUserData) }
            result.onSuccess {
                registersView.registerSuccessThenGoToLoginScreen(it.message)
            }.onFailure {
                if (result.getOrNull()?.message == null) {
                    registersView.showError((NullPointerException("No response body!")))
                }
            }
        }
    }
}
