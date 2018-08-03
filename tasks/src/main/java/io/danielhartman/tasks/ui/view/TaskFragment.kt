package io.danielhartman.tasks.ui.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.danielhartman.tasks.R
import io.danielhartman.tasks.model.Task
import io.danielhartman.tasks.model.TaskList
import kotlinx.android.synthetic.main.task_fragment.*
import me.danielhartman.common.core.Store

class TaskFragment : Fragment() {
    val vm = ViewTaskVM()
    private val adapter = TaskAdapter(){
       vm.onTaskClicked(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vm.model.taskList.observe(this, Observer {
            title.text = it?.name
        })
        vm.model.taskListDisplayModel.observe(this, Observer { adapter.submitList(it?:ArrayList()) })
    }

    override fun onResume() {
        super.onResume()
        vm.getList()
    }
}