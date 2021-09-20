package com.binhjdev.taskie.data.api

import com.binhjdev.taskie.data.model.request.AddTaskRequest
import com.binhjdev.taskie.data.model.request.LoginUserDataRequest
import com.binhjdev.taskie.data.model.request.RegisterUserDataRequest
import com.binhjdev.taskie.data.model.response.*
import com.binhjdev.taskie.ui.main.dialog.TaskDeleteDialogFragment
import retrofit2.http.*

interface TaskieApiService {
    @GET("/api/note")
    suspend fun getTasks(@Header("Authorization") token: String): TaskResponse

    @POST("/api/register")
    suspend fun registerUser(@Body request: RegisterUserDataRequest): UserRegisterResponse

    @POST("/api/login")
    suspend fun loginUser(@Body request: LoginUserDataRequest): TokenLoginResponse

    @POST("/api/note")
    suspend fun addTask(@Header("Authorization") token: String, @Body request: AddTaskRequest): AddTaskResponse

    @GET("/api/user/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): GetUserProfileResponse

    @DELETE("/api/note")
    suspend fun deleteTask(@Header("Authorization") token: String, @Query("id") noteId: String): DeleteTaskResponse
}
