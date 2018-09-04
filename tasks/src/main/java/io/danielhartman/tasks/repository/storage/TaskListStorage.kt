package io.danielhartman.tasks.repository.storage

import io.danielhartman.tasks.model.CompletionState
import io.danielhartman.tasks.model.Status
import io.danielhartman.tasks.model.Task
import io.danielhartman.tasks.model.TaskList
import me.danielhartman.common.core.CoreResponse
import me.danielhartman.common.core.Storage

class TaskListStorage : Storage<TaskList> {
    override fun write(data: TaskList, cb: (CoreResponse<TaskList>) -> Unit) {
        tasklist = data
        cb(CoreResponse.Success(tasklist))
    }

    override fun read(identifier: Any?, cb: (CoreResponse<TaskList>) -> Unit) {
        cb(CoreResponse.Success(tasklist))
    }

    override fun tryCache(): Boolean {
        return true
    }

    companion object {
        var tasklist = TaskList(name = "TaskList demo")
        val taskOne = Task(1, "TaskOne")
        val taskTwo = Task(2, "TaskTwo")
        val taskThree = Task(3, "Taskthree", status = Status(CompletionState.COMPLETED))

        init {
            tasklist.put(taskOne)
            tasklist.put(taskTwo)
            tasklist.put(taskThree)
        }

    }
}