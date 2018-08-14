package me.danielhartman.common.core

import com.sun.org.apache.xpath.internal.operations.Bool

interface Network<R>{
    fun request(identifier:Any?, type:RequestType, cb:(CoreResponse<R>)->Unit)
    fun shouldCacheResponse()= true
}