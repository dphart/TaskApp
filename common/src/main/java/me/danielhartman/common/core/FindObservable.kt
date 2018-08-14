package me.danielhartman.common.core

class FindObservable<T>(private val storage: InternalStorage<T>, private val network: InternalNetwork<T>) : Observable<T>() {
    fun find(identifier: Any? = null, tryCache: Boolean): Observable<T> {
        val defaultExecutor = ThreadStore.pool
        val runner = Runnable {
            workerThread?.run {
                storage.lookFor(tryCache, identifier) {
                    handleStorageResult(identifier, it)
                }
            } ?: defaultExecutor.run {
                storage.lookFor(tryCache, identifier) {
                    handleStorageResult(identifier, it)
                }
            }
        }
        addToQueue(runner)
        return this
    }

    private fun handleStorageResult(identifier: Any?, response: CoreResponse<T>) {
        when (response) {
            is CoreResponse.Success -> {
                push(response)
            }
            else -> {
                network.tryNetwork(identifier, RequestType.GET(null, HashMap())) { networkResponse ->
                    when (networkResponse) {
                        is CoreResponse.Success -> {
                            storage.save(networkResponse.data){
                                //Save callback currently ignored
                            }
                            push(networkResponse)
                        }
                        is CoreResponse.Error -> {
                            push(networkResponse)
                        }
                    }
                }

            }
        }
    }
}
