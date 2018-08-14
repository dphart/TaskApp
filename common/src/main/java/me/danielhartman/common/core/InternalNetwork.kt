package me.danielhartman.common.core

class InternalNetwork<R>(val network:Network<R>?){
    fun tryNetwork(identifier:Any?, requestType: RequestType, cb:(CoreResponse<R>)->Unit){
            network?.let {
                it.request(identifier, requestType) { networkResponse ->  cb(networkResponse) }
            }?:
            cb(CoreResponse.Error("Networking error"))

    }


}