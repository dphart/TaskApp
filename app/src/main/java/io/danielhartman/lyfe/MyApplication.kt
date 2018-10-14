package io.danielhartman.lyfe

import android.app.Application
import io.danielhartman.tasks.TaskAppInit

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        TaskAppInit.init(this)
    }
}