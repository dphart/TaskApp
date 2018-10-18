package io.danielhartman.tasks.ui.viewlists

import io.danielhartman.tasks.model.TaskList

data class TaskListModel(val id:Long, var listName:String, val taskList:TaskList) {
    init {
        if (listName.isEmpty()){
            listName = "NoNameFound"
        }
    }

}