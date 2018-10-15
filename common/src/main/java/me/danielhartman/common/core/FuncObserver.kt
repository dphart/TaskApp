package me.danielhartman.common.core

class FuncObserver<T>(){
    fun ask(input:(CoreResponse<T>) -> Unit):Observer<T> {
        return object : Observer<T>{
            override fun onData(response: CoreResponse<T>) {
                input.invoke(response)
            }

        }
    }

}