package io.danielhartman.tasks.repository.storage

import io.danielhartman.tasks.model.TaskList
import io.paperdb.Paper
import me.danielhartman.common.core.CoreResponse
import me.danielhartman.common.core.Storage

class FullTaskListStorage : Storage<List<TaskList>> {
    override fun read(identifier: (List<TaskList>) -> Boolean, cb: (CoreResponse<List<TaskList>>) -> Unit) {
        val book = Paper.book(TaskListStorage.taskListKey)
        val list: MutableList<TaskList> = ArrayList()
                book.allKeys.asSequence().map { book.read<TaskList>(it) }.map { list.add(it) }
        if (list.isNotEmpty()) {
            cb(CoreResponse.Success(list))
        } else {
            cb(CoreResponse.Error("Failure"))
        }
    }

    override fun write(data: List<TaskList>, cb: (CoreResponse<List<TaskList>>) -> Unit) {
        val taskListStorage = TaskListStorage()
        for (taskList in data) {
            taskListStorage.write(taskList) {
                //noop
            }
        }
        cb(CoreResponse.Success(data))
    }

}