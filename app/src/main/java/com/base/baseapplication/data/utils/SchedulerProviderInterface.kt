package com.base.baseapplication.data.utils

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface SchedulerProviderInterface {

    fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T>

    fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T>

    fun getSchedulersForCompletable(): (Completable) -> Completable

    fun <T> getSchedulersForFlowable(): (Flowable<T>) -> Flowable<T>

}