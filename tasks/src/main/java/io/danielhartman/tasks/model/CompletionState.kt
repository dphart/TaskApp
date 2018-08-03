package io.danielhartman.tasks.model


enum class CompletionState(var id: Long) {
    COMPLETED(0),
    INPROGRESS(1),
    READY(2)
}