package com.base.baseapplication.data.network.utils

import io.reactivex.disposables.Disposable
import java.util.concurrent.ConcurrentHashMap

open class RequestTagManager<T> {

    private val observableHashMap = ConcurrentHashMap<String, T>()
    private val disposableHashMap = ConcurrentHashMap<String, Disposable>()

    fun addObservableByTag(requestTag : String?, observable : T) {
        if (!requestTag.isNullOrEmpty()) {
            observableHashMap[requestTag] = observable
        }
    }

    fun removeObservableByTag(requestTag : String?) {
        if (!requestTag.isNullOrEmpty()) {
            observableHashMap.remove(requestTag)
        }
    }

    fun getObservableByTag(requestTag : String?) : T? {
        return if (!requestTag.isNullOrEmpty() && observableHashMap.containsKey(requestTag)) {
            observableHashMap[requestTag]
        } else {
            null
        }
    }

    fun removeDisposableByTag(requestTag : String?) {
        if (!requestTag.isNullOrEmpty() && disposableHashMap.containsKey(requestTag)) {
            disposableHashMap[requestTag]?.dispose()
            disposableHashMap.remove(requestTag)
        }
    }

    fun addDisposableByTag(requestTag : String?, disposable : Disposable) {
        if (!requestTag.isNullOrEmpty()) {
            if (disposableHashMap.containsKey(requestTag)) {
                disposableHashMap[requestTag]?.dispose()
                disposableHashMap.remove(requestTag)
            }
            disposableHashMap[requestTag] = disposable
        }
    }

    fun cancelRequestByTag(requestTag : String?) {
        if (!requestTag.isNullOrEmpty()) {
            val disposable = disposableHashMap[requestTag]
            if (disposable != null) {
                if (!disposable.isDisposed) {
                    disposable.dispose()
                }
                disposableHashMap.remove(requestTag)
            }
        }
    }

    fun clearRequestTag(requestTag : String?) {
        requestTag?.let {
            removeDisposableByTag(requestTag)
            removeObservableByTag(requestTag)
        }
    }
}