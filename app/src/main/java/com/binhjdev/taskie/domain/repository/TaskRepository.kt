package com.binhjdev.taskie.domain.repository

import com.binhjdev.taskie.data.model.Task
import com.binhjdev.taskie.data.model.request.AddTaskRequest
import com.binhjdev.taskie.data.model.request.LoginUserDataRequest
import com.binhjdev.taskie.data.model.request.RegisterUserDataRequest
import com.binhjdev.taskie.data.model.response.*

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun registerUser(requestUserRegister: RegisterUserDataRequest): UserRegisterResponse
    suspend fun loginUser(requestUserLogin: LoginUserDataRequest): TokenLoginResponse
    suspend fun addTask(requestAddTaskRequest: AddTaskRequest): AddTaskResponse
    suspend fun getUserProfile(): GetUserProfileResponse
    suspend fun deletedTask(taskId : String): DeleteTaskResponse
}