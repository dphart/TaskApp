@file:Suppress("PrivatePropertyName")

package io.danielhartman.tasks.ui.view

import android.graphics.Paint
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.danielhartman.tasks.R
import io.danielhartman.tasks.model.Task
import kotlinx.android.synthetic.main.task_completed_row.view.*
import kotlinx.android.synthetic.main.task_open_row.view.*

class TaskAdapter(f:(Task)->Unit) : ListAdapter<TaskListDisplayModel, TaskAdapter.BaseHolder>(TaskDiffUtilCallback()) {

    private val COMPLETED = 1
    private val OPEN = 2
    private val DIVIDER = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when(viewType){
            COMPLETED -> {CompletedHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_completed_row, parent, false)) }
            OPEN -> {OpenHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_open_row, parent, false))}
            DIVIDER -> {DividerHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_divider, parent, false))}
            else -> {DividerHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_open_row, parent, false))}
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentObj = getItem(position)
        return when(currentObj){
            is TaskListDisplayModel.CompletedTask -> 1
            is TaskListDisplayModel.OpenTask -> 2
            is TaskListDisplayModel.Divider -> 3
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {

        val currentObj = getItem(position)
        val task = when(currentObj){
            is TaskListDisplayModel.CompletedTask -> currentObj.task
            is TaskListDisplayModel.OpenTask -> currentObj.task
            else -> null
        }
        holder.display(task)
    }

    abstract class BaseHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun display(task:Task?)

    }
    class OpenHolder(view:View):BaseHolder(view){
        override fun display(task: Task?) {
            itemView.task_text_open.text = task?.name
        }
    }
    class CompletedHolder(view:View):BaseHolder(view){
        override fun display(task: Task?) {
            itemView.task_text_completed.text = task?.name
            itemView.task_text_completed.paintFlags = itemView.task_text_completed.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
    class DividerHolder(view:View):BaseHolder(view){
        override fun display(task: Task?) {
        }
    }

    class TaskDiffUtilCallback():DiffUtil.ItemCallback<TaskListDisplayModel>() {
        override fun areItemsTheSame(oldItem: TaskListDisplayModel?, newItem: TaskListDisplayModel?): Boolean {
            return if (oldItem is TaskListDisplayModel.OpenTask && newItem is TaskListDisplayModel.OpenTask){
                oldItem.task.id == newItem.task.id
            } else if (oldItem is TaskListDisplayModel.CompletedTask && newItem is TaskListDisplayModel.CompletedTask){
                oldItem.task.id == newItem.task.id
            } else {
                return oldItem is TaskListDisplayModel.Divider && newItem is TaskListDisplayModel.Divider
            }
//            return false
        }

        override fun areContentsTheSame(oldItem: TaskListDisplayModel?, newItem: TaskListDisplayModel?): Boolean {
            return if (oldItem is TaskListDisplayModel.OpenTask && newItem is TaskListDisplayModel.OpenTask){
                oldItem.task == newItem.task
            } else if (oldItem is TaskListDisplayModel.CompletedTask && newItem is TaskListDisplayModel.CompletedTask){
                oldItem.task == newItem.task
            } else {
                return oldItem is TaskListDisplayModel.Divider && newItem is TaskListDisplayModel.Divider
            }
//            return false
//
        }


    }
}

