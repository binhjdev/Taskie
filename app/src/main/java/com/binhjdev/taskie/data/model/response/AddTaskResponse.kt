package com.binhjdev.taskie.data.model.response

class AddTaskResponse(
        val id: String,
        val userId: String,
        val title: String,
        val content: String,
        val isCompleted: Boolean,
        val taskPriority: Int
)