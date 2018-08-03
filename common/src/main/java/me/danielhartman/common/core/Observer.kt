package me.danielhartman.common.core

interface Observer<T>{
    fun onData(response:CoreResponse<T>)
}