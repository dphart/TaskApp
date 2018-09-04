package me.danielhartman.common.core

class Store<R> {
    private var storage: Storage<R>? = null
    private var network: Network<R>? = null
    private var internalStorage = InternalStorage(storage)
    private var internalNetwork = InternalNetwork(network)
    val defaultExecutor = ThreadStore.pool

    fun read(identifier: Any? = null, tryCache: Boolean = true): Observable<R> {
        return FindObservable(internalStorage, internalNetwork).find(identifier, tryCache)
    }

    fun create(newItem: R, cb:((CoreResponse<R>) -> R)? = null) : Observable<R>  {
        return CreateObservable(internalStorage).create(newItem, cb)
    }

    fun update(item: R, cb:((CoreResponse<R>) -> R)? = null) : Observable<R> {
        return UpdateObservable(internalStorage).update(item, cb)
    }

    fun delete(item: R) {

    }

    fun setStorage(storage: Storage<R>): Store<R> {
        this.storage = storage
        this.internalStorage = InternalStorage(storage)
        return this
    }
    fun setNetwork(network: Network<R>): Store<R> {
        this.network = network
        this.internalNetwork = InternalNetwork(network)
        return this
    }
}