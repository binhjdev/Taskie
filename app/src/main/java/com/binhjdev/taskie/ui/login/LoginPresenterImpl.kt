package com.binhjdev.taskie.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binhjdev.taskie.data.model.request.LoginUserDataRequest
import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.utils.logCoroutine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LoginPresenterImpl(private val taskRepository: TaskRepository) : ViewModel(), LoginPresenter {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        throwable.printStackTrace()
    }

    private lateinit var loginsView: LoginView

    override fun setLoginView(loginView: LoginView) {
        this.loginsView = loginView
    }

    override fun sendLoginUserData(loginUserData: LoginUserDataRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            logCoroutine("login user", coroutineContext)
            val result = runCatching { taskRepository.loginUser(loginUserData) }
            result.onSuccess {
                loginsView.loginSuccessThenGoMainScreen(it.token)
            }.onFailure {
                if (result.getOrNull()?.token == null) {
                    loginsView.showError(NullPointerException("No response body!"))
                }
            }
        }
    }
}
