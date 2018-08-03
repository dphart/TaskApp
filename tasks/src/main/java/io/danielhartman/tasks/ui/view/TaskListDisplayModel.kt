package io.danielhartman.tasks.ui.view

import io.danielhartman.tasks.model.Task

sealed class TaskListDisplayModel {
    class CompletedTask(val task:Task):TaskListDisplayModel()
    class OpenTask(val task:Task):TaskListDisplayModel()
    class Divider():TaskListDisplayModel()
}