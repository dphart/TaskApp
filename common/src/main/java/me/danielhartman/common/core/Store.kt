package me.danielhartman.common.core

class Store<R> {
    private var storage: Storage<R>? = null
    private var network: Network<R>? = null
    private var internalStorage = InternalStorage(storage)
    private var internalNetwork = InternalNetwork(network)

    fun read(tryCache: Boolean = true,identifier: ((R)->Boolean)): Observable<R> {
        return FindObservable(internalStorage, internalNetwork).find(identifier, tryCache)
    }

    fun create(newItem: R): Observable<R> {
        return CreateObservable(internalStorage).create(newItem)
    }

    fun update(item: R): Observable<R> {
        return UpdateObservable(internalStorage).update(item)
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