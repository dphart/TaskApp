package me.danielhartman.common.core

import java.util.*
import java.util.concurrent.Executors

open class Observable<T> {
    private val observers = ArrayList<Observer<T>>()
    private val queue: Queue<Runnable> = LinkedList<Runnable>()
    private val executor = Executors.newSingleThreadExecutor()
    var responseThread:Thread? = null
    var workerThread:Thread? = null

    fun subscribe(observer: Observer<T>):Observer<T> {
        observers.add(observer)
        runQueue()
        return observer
    }

    fun unsubscribe(observer: Observer<T>) {
        observers.remove(observer)
    }

    fun unsubscribeAll() {
        observers.clear()
    }

    fun push(response: CoreResponse<T>) {
        for (observer in observers) {
            val thread = responseThread ?: Thread.currentThread()
            thread.run {
                observer.onData(response)
            }
        }
    }

    private fun runQueue() {
        executor.run {
            while (observers.size > 0 && queue.isNotEmpty()) {
                queue.poll().run()
            }
        }
    }

    fun addToQueue(runnable: Runnable) {
        queue.add(runnable)
        runQueue()
    }

    fun setWorkerThread(thread:Thread):Observable<T>{
        this.workerThread = thread
        return this
    }

    fun setResponseThread(thread:Thread):Observable<T>{
        this.responseThread = thread
        return this
    }
}