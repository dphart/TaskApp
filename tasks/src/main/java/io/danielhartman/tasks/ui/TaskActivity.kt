package io.danielhartman.tasks.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.danielhartman.tasks.R
import io.danielhartman.tasks.ui.view.TaskFragment
import io.danielhartman.tasks.ui.viewlists.ViewListFragment

class TaskActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.container, ViewListFragment()).commit()
        }
    }
}