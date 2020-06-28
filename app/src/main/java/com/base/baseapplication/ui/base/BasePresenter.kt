package com.base.baseapplication.ui.base

interface BasePresenter <V> {

    fun onAttach(baseView: V)

    fun onDetach()

    fun clear()
}