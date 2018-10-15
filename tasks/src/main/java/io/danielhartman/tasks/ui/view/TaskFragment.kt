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
import io.danielhartman.tasks.ui.create.CreateFragment
import io.danielhartman.tasks.ui.create.CreateFragment.CreateResponse.CreateSuccess
import kotlinx.android.synthetic.main.task_fragment.*

class TaskFragment : Fragment() {
    val vm = ViewTaskVM(1)
    private val adapter = TaskAdapter(){ task, checked ->
       vm.onTaskClicked(task, checked)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vm.model.taskList.observe(this, Observer {
            title.text = it?.name
            if (title.text.isEmpty()) title.text = "Task List"
        })
        vm.model.taskListDisplayModel.observe(this, Observer { adapter.submitList(it?:ArrayList()) })
        add_task_button.setOnClickListener { handleAddClicked() }
    }

    private fun handleAddClicked() {
        val id = vm.model.taskList.value?.id ?: TaskList.NO_LIST
        val frag:CreateFragment = CreateFragment.getInstance(id)
        frag.response = {
            onCreateResponse(it)
            frag.dismiss()
        }
        frag.show(activity?.supportFragmentManager, "CreateFragment")
    }
    private fun onCreateResponse(response:CreateFragment.CreateResponse){
        when(response){
            is CreateSuccess -> vm.taskAddedToList(response.task)
            is CreateFragment.CreateResponse.MyTasks -> {
                //Todo add manage tasks screen
            }
        }

    }

    override fun onResume() {
        super.onResume()
        vm.getList()
    }


}