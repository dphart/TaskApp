package me.danielhartman.common.core

class CreateObservable<T>(val storage: InternalStorage<T>):Observable<T>(){
    fun create(item:T) : Observable<T>{
        val runnable = Runnable {
            storage.save(item) {
                push(it)
            }
        }
        addToQueue(runnable)
        return this
    }
}