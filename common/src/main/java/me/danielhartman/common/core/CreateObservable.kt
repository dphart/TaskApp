package me.danielhartman.common.core

class CreateObservable<T>(val storage: InternalStorage<T>):Observable<T>(){
    fun create(item:T, cb:((CoreResponse<T>) -> T)?) : Observable<T>{
        val runnable = Runnable {
            storage.save(item) {
                cb?.invoke(it)
            }
        }
        addToQueue(runnable)
        return this
    }
}