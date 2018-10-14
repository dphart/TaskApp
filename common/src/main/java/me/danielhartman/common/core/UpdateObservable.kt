package me.danielhartman.common.core

 class UpdateObservable<T>(val storage:InternalStorage<T>) : Observable<T>(){
     fun update(item:T) : Observable<T>{
         val runnable = Runnable {
             storage.save(item) {
                 push(it)
             }
         }
         addToQueue(runnable)
         return this
     }


 }
