package io.danielhartman.tasks.ui.create

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.danielhartman.tasks.R
import io.danielhartman.tasks.model.Task
import io.danielhartman.tasks.model.TaskList
import kotlinx.android.synthetic.main.create_fragment.*
import kotlinx.android.synthetic.main.task_fragment.*

class CreateFragment : DialogFragment() {

    private var taskId: Long = -1

    var response:((CreateResponse) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskId = arguments?.getLong(taskkey) ?: TaskList.NO_LIST
        create_button.setOnClickListener{ createButtonClick() }
        my_tasks_button.setOnClickListener { response?.invoke(CreateResponse.MyTasks()) }
    }

    private fun createButtonClick(){
        val titleText = name_input_edittext.text.toString()
        if (titleText.isNotEmpty()){
            response?.invoke(CreateResponse.CreateSuccess(Task(name = titleText)))
        } else {
            title_input.error = "Please enter a Title"
        }
    }

    companion object {
        const val taskkey = "taskkey"
        fun getInstance(listId: Long?): CreateFragment {
            val frag = CreateFragment()
            val bundle = Bundle()

            bundle.putLong(taskkey, listId ?: TaskList.NO_LIST)
            frag.arguments = bundle

            return frag
        }
    }

    sealed class CreateResponse {
        class CreateSuccess(val task:Task) : CreateResponse()
        class MyTasks():CreateResponse()
    }

}