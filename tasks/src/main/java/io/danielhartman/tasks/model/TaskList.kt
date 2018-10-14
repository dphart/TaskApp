package io.danielhartman.tasks.model

import java.time.DayOfWeek
import java.util.*
import kotlin.collections.ArrayList

data class TaskList(
        val id:Long = NO_LIST,
        var tasks: ArrayList<Task> = ArrayList(),
        var dateInMilli: Long = Calendar.getInstance().timeInMillis,
        var name:String = "",
        var repeats:List<RepeatStrategy> = ArrayList() ) {

    fun put(task:Task){
        tasks.add(task)
    }

    fun put(taskList:ArrayList<Task>){
        for (task in taskList){
            put(task)
        }
    }

    companion object {
        const val NO_LIST = -1L
    }

}