package com.base.baseapplication.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFragmentActivity : BaseActivity() {

    val fragmentTransaction = supportFragmentManager.beginTransaction()

    fun setOnBackStackChangedListener(listener : FragmentManager.OnBackStackChangedListener) {
        supportFragmentManager.addOnBackStackChangedListener(listener)
    }

    fun getCurrentContainerFragment(containerId : Int) : Fragment? {
        return supportFragmentManager.findFragmentById(containerId)
    }

    fun getContainerFragmentByTag(tag : String) : Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

}