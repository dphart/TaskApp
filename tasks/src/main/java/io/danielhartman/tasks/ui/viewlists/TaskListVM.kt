package io.danielhartman.tasks.ui.viewlists

import android.arch.lifecycle.MutableLiveData
import io.danielhartman.tasks.model.TaskList
import io.danielhartman.tasks.repository.storage.TaskListStorage
import me.danielhartman.common.core.CoreResponse
import me.danielhartman.common.core.FuncObserver
import me.danielhartman.common.core.Observer
import me.danielhartman.common.core.Store

class TaskListVM {
    val model = MutableLiveData<TaskListModel>()
    val taskListStore = Store<TaskList>().setStorage(TaskListStorage())
    fun getData(){
        taskListStore.read { true }.subscribe(FuncObserver<TaskList>().ask {
            when(it){
                is CoreResponse.Success -> model.value = it.data.toTaskListModel()
                is CoreResponse.Error -> TODO()
            }
        })
    }

    fun TaskList.toTaskListModel():TaskListModel{

    }
}