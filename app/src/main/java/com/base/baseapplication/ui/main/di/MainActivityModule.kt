package com.base.baseapplication.ui.main.di

import com.base.baseapplication.di.scope.ActivityScoped
import com.base.baseapplication.repository.CarDataService
import com.base.baseapplication.repository.CarDataServiceRepository
import com.base.baseapplication.ui.main.MainContract
import com.base.baseapplication.ui.main.MainPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideCarDataServiceRepository(apiService: CarDataServiceRepository) : CarDataService

    @Binds
    @ActivityScoped
    abstract fun provideMainActivityPresenter(mainPresenterImpl: MainPresenterImpl) : MainContract.Presenter

}