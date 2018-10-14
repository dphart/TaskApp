package io.danielhartman.tasks.model

import java.time.DayOfWeek

data class RepeatStrategy(
        val day:List<Day> = ArrayList(),
        val time:Time
) {

}