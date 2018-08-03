package me.danielhartman.common.core

interface Storage<R> {
    fun read(identifier:Any?, cb:(CoreResponse<R>) -> Unit)
    fun tryCache() = true
    fun write(data:R, cb:(CoreResponse<R>) -> Unit)
}