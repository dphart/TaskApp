package me.danielhartman.common.core

class InternalStorage<R>(val storage:Storage<R>?) {

    fun lookFor(tryCache: Boolean, identifier: ((R)->Boolean), cb: (CoreResponse<R>) -> Unit) {
        var cResponse: CoreResponse<R> = CoreResponse.Error("No Data found")
        if (tryCache && storage?.tryCache() == true) {
            storage.read(identifier) { cResponse = it }
        }
        cb(cResponse)
    }
    fun save(data:R, cb:(CoreResponse<R>)->Unit) {
        var cResponse: CoreResponse<R> = CoreResponse.Error("Could not save")
        storage?.write(data) {  cResponse = it  }
        cb(cResponse)
    }
}
