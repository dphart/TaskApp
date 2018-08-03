package io.danielhartman.tasks.model

import java.util.*
import kotlin.collections.ArrayList

data class TaskList(
        var tasks: ArrayList<Task> = ArrayList(),
        var dateInMilli: Long = Calendar.getInstance().timeInMillis,
        var name:String = "") {

    fun put(task:Task){
        tasks.add(task)
    }

    fun put(taskList:ArrayList<Task>){
        for (task in taskList){
            put(task)
        }
    }

}