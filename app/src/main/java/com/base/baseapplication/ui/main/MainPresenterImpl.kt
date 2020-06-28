package com.base.baseapplication.ui.main

import com.base.baseapplication.data.network.models.CarList
import com.base.baseapplication.data.network.utils.RequestTagManager
import com.base.baseapplication.di.scope.ActivityScoped
import com.base.baseapplication.ui.base.BasePresenterImpl
import com.base.baseapplication.data.utils.SchedulerProviderInterface
import com.base.baseapplication.repository.CarDataService
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@ActivityScoped
class MainPresenterImpl @Inject constructor(private var carDataService : CarDataService,
                                            private var schedulerProvider : SchedulerProviderInterface)
    : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

    private val requestTagManager = RequestTagManager<Observable<CarList>>()

    override fun getCarList(requestTag : String?) {
        val observable = carDataService.getCars()
        observable.compose(schedulerProvider.getSchedulersForObservable())
                .doFinally {
                    view?.hideLoading()
                    requestTagManager.clearRequestTag(requestTag)
                }
                .doOnSubscribe {
                    view?.showLoading()
                    requestTagManager.addDisposableByTag(requestTag, it)
                }
                .subscribe({
                    view?.updateCarList(it)
                }, {
                    view?.showToast(it.toString())
                }).addTo(compositeDisposables)

    }

    override fun getAvailability(id: Int, requestTag : String?) {
        val observable = carDataService.getAvailability(id)
        observable.compose(schedulerProvider.getSchedulersForObservable())
                .doFinally {
                    view?.hideLoading()
                    requestTagManager.clearRequestTag(requestTag)
                }
                .doOnSubscribe {
                    view?.showLoading()
                    requestTagManager.addDisposableByTag(requestTag, it)
                }
                .subscribe({
                    view?.updateAvailability(id, it)
                }, {
                    view?.showToast(it.toString())
                }).addTo(compositeDisposables)

    }

}