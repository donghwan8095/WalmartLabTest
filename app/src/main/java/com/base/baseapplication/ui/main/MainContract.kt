package com.base.baseapplication.ui.main

import com.base.baseapplication.data.network.models.Availability
import com.base.baseapplication.data.network.models.CarList
import com.base.baseapplication.ui.base.BasePresenter
import com.base.baseapplication.ui.base.BaseView

interface MainContract {

    interface Presenter : BasePresenter<View> {
        fun getCarList(requestTag : String? = null)
        fun getAvailability(id: Int, requestTag : String? = null)
    }

    interface View : BaseView<Presenter> {
        fun updateCarList(carList : CarList)
        fun updateAvailability(id: Int, availability: Availability)
        fun showLoading()
        fun hideLoading()
        fun showToast(text : String)
    }

}