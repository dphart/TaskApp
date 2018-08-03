package me.danielhartman.common.core

sealed class CoreResponse<T> {
    class Success<T>(val data:T) : CoreResponse<T>()
    class Error<T>(val message:String) : CoreResponse<T>()
}