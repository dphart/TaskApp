package me.danielhartman.common.core

import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

object ThreadStore {
    var pool = Executors.newFixedThreadPool(10)

}