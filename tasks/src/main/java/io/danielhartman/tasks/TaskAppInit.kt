package io.danielhartman.tasks

import android.content.Context
import io.danielhartman.tasks.model.Task
import io.paperdb.Paper

class TaskAppInit {
    companion object {
        fun init(context: Context){
            Paper.init(context)
        }
    }
}