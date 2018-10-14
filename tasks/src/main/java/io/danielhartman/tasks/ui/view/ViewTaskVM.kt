package io.danielhartman.tasks.ui.view

import io.danielhartman.tasks.model.CompletionState
import io.danielhartman.tasks.model.Task
import io.danielhartman.tasks.model.TaskList
import io.danielhartman.tasks.repository.storage.TaskListStorage
import me.danielhartman.common.core.CoreResponse
import me.danielhartman.common.core.Observer
import me.danielhartman.common.core.Store

class ViewTaskVM(val id:Long) {

    val model = ViewTaskModel(id)
    val taskListStore = Store<TaskList>().setStorage(TaskListStorage())

    fun getList() {
       taskListStore.read{
           it.id == id
       }.subscribe(object : Observer<TaskList> {
            override fun onData(response: CoreResponse<TaskList>) {
                when (response) {
                    is CoreResponse.Success -> {
                        model.taskList.value = response.data
                        model.taskListDisplayModel.value = response.data.toDisplayModel()
                    }
                }
            }
        })
    }

    private fun TaskList.toDisplayModel(): List<TaskListDisplayModel> {
        val list = ArrayList<TaskListDisplayModel>()
        val open = ArrayList<TaskListDisplayModel.OpenTask>()
        val completed = ArrayList<TaskListDisplayModel.CompletedTask>()
        for (task in this.tasks) {
            when (task.status.state) {
                CompletionState.COMPLETED -> completed.add(TaskListDisplayModel.CompletedTask(task))
                else -> open.add(TaskListDisplayModel.OpenTask(task))
            }
        }
        list.addAll(open)
        if (completed.size > 0) {
            list.add(TaskListDisplayModel.Divider())
        }
        list.addAll(completed)
        return list.toList()
    }

    fun onTaskClicked(task: Task, checked:Boolean) {
        model.taskList.value?.tasks?.find { task == it }?.status?.state = if (checked) CompletionState.COMPLETED else CompletionState.READY
        taskListStore.update(model.taskList.value!!).subscribe(object : Observer<TaskList> {
            override fun onData(response: CoreResponse<TaskList>) {
                getList()
            }
        })
    }

    fun taskAddedToList(task: Task) {
        val taskList = model.taskList.value ?: TaskList()
        taskList.put(task)
        taskListStore.update(taskList).subscribe(object : Observer<TaskList> {
            override fun onData(response: CoreResponse<TaskList>) {
                getList()
            }
        })
    }
}