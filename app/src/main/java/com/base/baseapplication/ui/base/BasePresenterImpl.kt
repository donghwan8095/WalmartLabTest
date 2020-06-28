package com.base.baseapplication.ui.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenterImpl<V> : BasePresenter<V> {

    protected var view: V? = null
    protected val compositeDisposables = CompositeDisposable()

    override fun onAttach(baseView: V) {
        this.view = baseView
    }

    override fun onDetach() {
        view = null
        clear()
    }

    override fun clear() {
        compositeDisposables.clear()
    }

}