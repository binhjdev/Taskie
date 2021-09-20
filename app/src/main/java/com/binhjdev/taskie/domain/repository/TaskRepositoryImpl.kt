package com.binhjdev.taskie.domain.repository

import com.binhjdev.taskie.TaskieApplication
import com.binhjdev.taskie.data.api.TaskieApiService
import com.binhjdev.taskie.data.model.Task
import com.binhjdev.taskie.data.model.request.AddTaskRequest
import com.binhjdev.taskie.data.model.request.LoginUserDataRequest
import com.binhjdev.taskie.data.model.request.RegisterUserDataRequest
import com.binhjdev.taskie.data.model.response.*

class TaskRepositoryImpl(
        val taskieApiService: TaskieApiService
) : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        val getTask = try {
            taskieApiService.getTasks(TaskieApplication.getToken()).notes
        } catch (error: Throwable) {
            null
        }

        return getTask!!
    }

    override suspend fun registerUser(requestUserRegister: RegisterUserDataRequest): UserRegisterResponse {
        val successRegisterMessage = try {
            taskieApiService.registerUser(requestUserRegister)
        } catch (error: Throwable) {
            null
        }

        return successRegisterMessage!!
    }

    override suspend fun loginUser(requestUserLogin: LoginUserDataRequest): TokenLoginResponse {
        val successLoginThenReturnToken = try {
            taskieApiService.loginUser(requestUserLogin)
        } catch (error: Throwable) {
            null
        }

        return successLoginThenReturnToken!!
    }

    override suspend fun addTask(requestAddTaskRequest: AddTaskRequest): AddTaskResponse {
        val addNewTaskSuccessThenReturnTask = try {
            taskieApiService.addTask(TaskieApplication.getToken(), requestAddTaskRequest)
        } catch (error: Throwable) {
            null
        }

        return addNewTaskSuccessThenReturnTask!!
    }

    override suspend fun getUserProfile(): GetUserProfileResponse {
        val successGetProfile = try {
            taskieApiService.getUserProfile(TaskieApplication.getToken())
        } catch (error: Throwable) {
            null
        }

        return successGetProfile!!
    }

    override suspend fun deletedTask(taskId: String): DeleteTaskResponse {
        val successDeletedMessage = try {
            taskieApiService.deleteTask(TaskieApplication.getToken(), taskId)
        } catch (error: Throwable) {
            null
        }

        return successDeletedMessage!!
    }
}
