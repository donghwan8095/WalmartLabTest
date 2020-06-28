package com.base.baseapplication.di.module

import com.base.baseapplication.di.scope.ActivityScoped
import com.base.baseapplication.ui.main.MainActivity
import com.base.baseapplication.ui.main.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}