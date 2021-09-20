package com.binhjdev.taskie.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.binhjdev.taskie.R
import com.binhjdev.taskie.data.model.PriorityColor
import com.binhjdev.taskie.data.model.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
        private val onItemLongClick: (String) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.showData(tasks[position], onItemLongClick)
    }

    override fun getItemCount() = tasks.size

    fun setData(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    fun removeTask(taskId: String) {
        val taskIndex = tasks.indexOfFirst { it.id == taskId }

        if (taskIndex != -1) {
            tasks.removeAt(taskIndex)
            notifyItemRemoved(taskIndex)
        }
    }
}

class TaskViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun showData(task: Task, onItemLongClick: (String) -> Unit) {
        containerView.setOnLongClickListener {
            onItemLongClick(task.id)
            true
        }

        containerView.textViewTitleItem.text = task.title
        containerView.textViewContentItem.text = task.content

        val drawable = containerView.checkPriority.drawable
        val wrapDrawable = DrawableCompat.wrap(drawable)

        val priorityColor = when (task.taskPriority) {
            1 -> PriorityColor.LOW
            2 -> PriorityColor.MEDIUM
            else -> PriorityColor.HIGH
        }
        DrawableCompat.setTint(wrapDrawable,
                ContextCompat.getColor(containerView.context, priorityColor.getColor()))
    }
}
