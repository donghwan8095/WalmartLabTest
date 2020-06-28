package com.base.baseapplication.data.utils

import io.reactivex.*

class SchedulerProvider(private val backgroundScheduler: Scheduler, private val foregroundScheduler: Scheduler) : SchedulerProviderInterface {

    override fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
        return { observable: Observable<T> ->
            observable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }

    override fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {
        return { single: Single<T> ->
            single.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }

    override fun getSchedulersForCompletable(): (Completable) -> Completable {
        return { completable: Completable ->
            completable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }

    override fun <T> getSchedulersForFlowable(): (Flowable<T>) -> Flowable<T> {
        return { flowable: Flowable<T> ->
            flowable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }
}