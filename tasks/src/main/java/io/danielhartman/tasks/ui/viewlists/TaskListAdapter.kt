package io.danielhartman.tasks.ui.viewlists

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.danielhartman.tasks.R
import kotlinx.android.synthetic.main.task_list_row.view.*

class TaskListAdapter(val cb:(TaskListModelClick) -> Unit) : ListAdapter<TaskListModel, TaskListAdapter.BaseViewHolder>(TaskListDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_row, parent,  true)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.populate(getItem(position), cb)
    }

    abstract class BaseViewHolder(view: View):RecyclerView.ViewHolder(view) {
        abstract fun populate(model:TaskListModel, cb:(TaskListModelClick) -> Unit )
    }

    class CardHolder(view:View) : BaseViewHolder(view){
        override fun populate(model: TaskListModel, cb:(TaskListModelClick) -> Unit) {
            itemView.list_title.text = model.listName
            itemView.task_list_row_container.setOnClickListener { cb(TaskListModelClick.ClickedTask(model)) }
        }
    }

    class TaskListDiffUtil:android.support.v7.util.DiffUtil.ItemCallback<TaskListModel>(){
        override fun areItemsTheSame(oldItem: TaskListModel?, newItem: TaskListModel?) = oldItem == newItem
        override fun areContentsTheSame(oldItem: TaskListModel?, newItem: TaskListModel?) = oldItem == newItem
    }
    sealed class TaskListModelClick(){
        class ClickedTask(task:TaskListModel) : TaskListModelClick()
    }
}