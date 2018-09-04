package me.danielhartman.common.core

 class UpdateObservable<T>(val storage:InternalStorage<T>) : Observable<T>(){
     fun update(item:T, cb:((CoreResponse<T>) -> T)?) : Observable<T>{
         val runnable = Runnable {
             storage.save(item) {
                 cb?.invoke(it)
             }
         }
         addToQueue(runnable)
         return this
     }


 }
