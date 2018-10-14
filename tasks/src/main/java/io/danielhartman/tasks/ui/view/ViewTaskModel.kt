package io.danielhartman.tasks.ui.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.danielhartman.tasks.model.TaskList

data class ViewTaskModel(
        val id:Long,
        val taskList:MutableLiveData<TaskList> = MutableLiveData<TaskList>(),
        val taskListDisplayModel: MutableLiveData<List<TaskListDisplayModel>> = MutableLiveData()
) {



}