package io.danielhartman.tasks.ui.viewlists

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.danielhartman.tasks.R
import kotlinx.android.synthetic.main.view_list_fragment.*

class ViewListFragment : Fragment() {
    val vm = TaskListVM()
    val adapter = TaskListAdapter {
      navigateToDetailView(it)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_list_fragment, container, false)
    }

    fun navigateToDetailView(taskListModelClick: TaskListAdapter.TaskListModelClick){

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        vm.getData()
        vm.model.observe(this, Observer<List<TaskListModel>> {
            adapter.submitList(it)
        })
    }
}