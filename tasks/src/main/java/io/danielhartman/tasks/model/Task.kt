package io.danielhartman.tasks.model

import java.util.*

data class Task(
        var id: Long = 0,
        var name: String = "",
        var description: String = "",
        var subTasks: List<Task> = ArrayList(),
        var status: Status = Status()) {
    fun complete() {
        this.status = status.setComplete()
    }
    fun isSubtask() = subTasks.isNotEmpty()


}