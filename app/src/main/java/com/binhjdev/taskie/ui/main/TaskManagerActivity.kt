package com.binhjdev.taskie.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.binhjdev.taskie.R
import com.binhjdev.taskie.data.model.Task
import com.binhjdev.taskie.ui.addNewTask.AddNewTaskActivity
import com.binhjdev.taskie.ui.main.dialog.TaskDeleteDialogFragment
import com.binhjdev.taskie.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.action_bar_main.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class TaskManagerActivity : AppCompatActivity(), TasksView, TaskDeleteDialogFragment.TaskSelectedListener {

    private val presenter: TasksPresenter by viewModel<TasksPresenterImpl>()
    private val taskAdapter by lazy { TaskAdapter(::onItemSelected) }

    override fun onStart() {
        super.onStart()
        presenter.getData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

        presenter.setViews(this)
    }

    private fun initUI() {
        imgBack.isVisible = false
        taskList.adapter = taskAdapter
        taskList.layoutManager = LinearLayoutManager(this)
        swipeRefresh.setOnRefreshListener { presenter.getData() }
        fabAddTask.setOnClickListener { goToAddTaskScreen() }
        imgViewUserInfo.setOnClickListener { goToProfileScreen() }
    }

    private fun onItemSelected(taskId: String) {
        val dialog = TaskDeleteDialogFragment.newInstance(taskId)
        dialog.setTaskOptionSelectedListener(this)
        dialog.show(supportFragmentManager, dialog.tag)
    }

    private fun goToProfileScreen() {
        startActivity(Intent(this@TaskManagerActivity, ProfileActivity::class.java))
    }

    private fun goToAddTaskScreen() {
        startActivity(Intent(this@TaskManagerActivity, AddNewTaskActivity::class.java))
    }

    override fun showTasks(tasks: List<Task>) {
        taskAdapter.setData(tasks)
        swipeRefresh.isRefreshing = false
    }

    override fun showError(error: Throwable) {
        taskAdapter.setData(mutableListOf())
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        swipeRefresh.isRefreshing = false
    }

    override fun onTaskDeleted(taskId: String) {
        taskAdapter.removeTask(taskId)
    }
}
