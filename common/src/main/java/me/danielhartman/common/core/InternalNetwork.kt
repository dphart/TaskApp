package me.danielhartman.common.core

class InternalNetwork<R>(val network:Network<R>?){
    fun tryNetwork(identifier:Any?, cb:(CoreResponse<R>)->Unit){
            network?.let {
                it.request(identifier) { networkResponse ->  cb(networkResponse) }
            }?:
            cb(CoreResponse.Error("Networking error"))

    }


}