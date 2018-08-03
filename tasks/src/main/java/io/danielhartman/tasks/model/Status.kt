package io.danielhartman.tasks.model


import java.util.*

data class Status(
        var state: CompletionState = CompletionState.READY,
        var dateCompleted: Long = -1, var id: Long = 0) {
    fun setComplete(): Status {
        state = CompletionState.COMPLETED
        dateCompleted = Calendar.getInstance().timeInMillis
        return this
    }
}
