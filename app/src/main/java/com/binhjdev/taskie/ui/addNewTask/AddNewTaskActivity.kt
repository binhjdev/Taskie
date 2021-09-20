package com.binhjdev.taskie.ui.addNewTask

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.binhjdev.taskie.R
import com.binhjdev.taskie.data.model.request.AddTaskRequest
import com.binhjdev.taskie.ui.addTaskConfirm.AddTaskConfirmActivity
import kotlinx.android.synthetic.main.action_bar_main.*
import kotlinx.android.synthetic.main.activity_add_task.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddNewTaskActivity : AppCompatActivity(), AddNewTaskView, View.OnClickListener {

    private val presenter: AddNewTaskPresenter by viewModel<AddNewTaskPresenterImpl>()
    private var taskPriority: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        initUI()

        presenter.setView(this)
    }

    private fun initUI() {
        titleBar.isVisible = false
        imgViewUserInfo.isVisible = false
        imgBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        btnAddNewTask.setThrottleClickListener {
            if (checkValidate(edtTitle.text.toString(), edtContent.text.toString())) {
                presenter.sendCreateTaskData(
                    AddTaskRequest(
                        edtTitle.text.toString(),
                        edtContent.text.toString(),
                        taskPriority
                    )
                )
            }
        }

        btnLow.setOnClickListener(this)
        btnMedium.setOnClickListener(this)
        btnHigh.setOnClickListener(this)
    }

    private fun checkValidate(title: String, content: String): Boolean {
        if (title.isBlank() || content.isBlank()) {
            Toast.makeText(this, "Please input field!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun goToConfirmAddNewTaskScreen(title: String, content: String) {
        val confirmIntent = Intent(this@AddNewTaskActivity, AddTaskConfirmActivity::class.java)
        confirmIntent.putExtra("title", title)
        confirmIntent.putExtra("content", content)
        confirmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(confirmIntent)
        finish()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnLow -> {
                taskPriority = 1
                btnLow.background = resources.getDrawable(R.drawable.button_selected_priority)
                tvLow.setTextColor(Color.parseColor("#FFFFFF"))
                btnMedium.background = resources.getDrawable(R.drawable.button_unselected_priority)
                tvMedium.setTextColor(Color.parseColor("#0A1931"))
                btnHigh.background = resources.getDrawable(R.drawable.button_unselected_priority)
                tvHigh.setTextColor(Color.parseColor("#0A1931"))
            }
            R.id.btnMedium -> {
                taskPriority = 2
                btnMedium.background = resources.getDrawable(R.drawable.button_selected_priority)
                tvMedium.setTextColor(Color.parseColor("#FFFFFF"))
                btnLow.background = resources.getDrawable(R.drawable.button_unselected_priority)
                tvLow.setTextColor(Color.parseColor("#0A1931"))
                btnHigh.background = resources.getDrawable(R.drawable.button_unselected_priority)
                tvHigh.setTextColor(Color.parseColor("#0A1931"))
            }
            R.id.btnHigh -> {
                taskPriority = 3
                btnHigh.background = resources.getDrawable(R.drawable.button_selected_priority)
                tvHigh.setTextColor(Color.parseColor("#FFFFFF"))
                btnLow.background = resources.getDrawable(R.drawable.button_unselected_priority)
                tvLow.setTextColor(Color.parseColor("#0A1931"))
                btnMedium.background = resources.getDrawable(R.drawable.button_unselected_priority)
                tvMedium.setTextColor(Color.parseColor("#0A1931"))
            }
        }
    }

    private fun View.setThrottleClickListener(throttle: Long = 200L, onClicked: (View?) -> Unit) {
        this.setOnClickListener { v ->
            v.isClickable = false
            v.postDelayed({
                v.isClickable = true
            }, throttle)
            onClicked(v)
        }
    }
}