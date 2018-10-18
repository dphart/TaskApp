package io.danielhartman.tasks.ui.viewlists

import android.arch.lifecycle.MutableLiveData
import io.danielhartman.tasks.model.TaskList
import io.danielhartman.tasks.repository.storage.FullTaskListStorage
import io.danielhartman.tasks.repository.storage.TaskListStorage
import me.danielhartman.common.core.CoreResponse
import me.danielhartman.common.core.FuncObserver
import me.danielhartman.common.core.Observer
import me.danielhartman.common.core.Store

class TaskListVM {
    val model = MutableLiveData<List<TaskListModel>>()
    val taskListStore = Store<List<TaskList>>().setStorage(FullTaskListStorage())
    fun getData(){
        taskListStore.read { true }.subscribe(FuncObserver<List<TaskList>>().ask {
            when(it){
                is CoreResponse.Success -> model.value = it.data.toTaskListModel()
                is CoreResponse.Error -> handleError(it)
            }
        })
    }

    fun handleError(error:CoreResponse.Error<List<TaskList>>){

    }

    private fun List<TaskList>.toTaskListModel():List<TaskListModel>{
        return this.map { TaskListModel(it.id, it.name, it) }
    }
}