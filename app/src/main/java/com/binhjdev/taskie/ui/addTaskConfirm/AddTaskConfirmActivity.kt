package com.binhjdev.taskie.ui.addTaskConfirm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binhjdev.taskie.R
import com.binhjdev.taskie.ui.addNewTask.AddNewTaskActivity
import com.binhjdev.taskie.ui.main.TaskManagerActivity
import kotlinx.android.synthetic.main.activity_confirm_add_task.*

class AddTaskConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_add_task)

        val intent = intent
        if (intent != null) {
            tvTitleConfirm.text = intent.getStringExtra("title")
            tvContentConfirm.text = intent.getStringExtra("content")
        }

        btnAddAnotherTask.setOnClickListener {
            startActivity(Intent(this@AddTaskConfirmActivity, AddNewTaskActivity::class.java))
            finish()
        }

        btnBackToHome.setOnClickListener {
            startActivity(Intent(this@AddTaskConfirmActivity, TaskManagerActivity::class.java))
            finish()
        }
    }
}
