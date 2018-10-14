package io.danielhartman.tasks.repository.storage

import io.danielhartman.tasks.model.CompletionState
import io.danielhartman.tasks.model.Status
import io.danielhartman.tasks.model.Task
import io.danielhartman.tasks.model.TaskList
import io.paperdb.Book
import io.paperdb.Paper
import me.danielhartman.common.core.CoreResponse
import me.danielhartman.common.core.Storage
import java.lang.Exception

class TaskListStorage : Storage<TaskList> {

    private val taskListKey = "tasklistkey"
    override fun write(data: TaskList, cb: (CoreResponse<TaskList>) -> Unit) {
        val taskKey = data.getKey(Paper.book(taskListKey))
        val returnTaskList = data.copy(id = taskKey)
        Paper.book(taskListKey).write( taskKey.toString(), returnTaskList )
        cb(CoreResponse.Success(returnTaskList))
    }

    fun Book.findMax():Long  {
        return this.allKeys.asSequence().map { it.toLong() }.max() ?: 0
    }
    private fun Book.autoincrementKey():Long {
        return this.findMax() + 1
    }
    private fun TaskList.getKey(values: Book):Long{
        return if (id == TaskList.NO_LIST) values.autoincrementKey() else id
    }



    override fun read(identifier: ((TaskList)->Boolean), cb: (CoreResponse<TaskList>) -> Unit) {

        val savedItemsThatMeetIdentifier = Paper.book(taskListKey).allKeys
                .asSequence()
                .map { Paper.book(taskListKey).read<TaskList>(it) }
                .filter { identifier.invoke(it) }
                .toList()


        if (savedItemsThatMeetIdentifier.isNotEmpty()) {
            cb(CoreResponse.Success(savedItemsThatMeetIdentifier.first()))
        } else {
            cb(CoreResponse.Error("Could not find any matching entries for ${identifier}"))
        }
    }

    override fun tryCache(): Boolean {
        return true
    }
}