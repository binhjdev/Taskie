package com.binhjdev.taskie.ui.main.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.binhjdev.taskie.R
import kotlinx.android.synthetic.main.fragment_dialog_delete_task.*
import org.koin.android.viewmodel.ext.android.viewModel

class TaskDeleteDialogFragment : DialogFragment(), DeleteTaskView {

    private var taskSelectedListener: TaskSelectedListener? = null
    private val presenter: DeleteTaskPresenter by viewModel<TaskDeletePresenterImpl>()

    companion object {
        private const val KEY_TASK_ID = "task_id"

        fun newInstance(taskId: String): TaskDeleteDialogFragment =
            TaskDeleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TASK_ID, taskId)
                }
            }
    }

    interface TaskSelectedListener {
        fun onTaskDeleted(taskId: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)

        presenter.setView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_delete_task, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val taskId = arguments?.getString(KEY_TASK_ID) ?: ""
        Log.d(KEY_TASK_ID, taskId)
        if (taskId.isEmpty()) dismissAllowingStateLoss()

        btnDeleted.setThrottleClickListener {
            presenter.sendTaskIdDeleteData(taskId)
            taskSelectedListener?.onTaskDeleted(taskId)
        }

        btnCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    fun setTaskOptionSelectedListener(taskSelectedListener: TaskSelectedListener) {
        this.taskSelectedListener = taskSelectedListener
    }

    override fun showMessageDeletedSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        dismissAllowingStateLoss()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
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