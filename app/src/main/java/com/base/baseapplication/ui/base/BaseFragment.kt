package com.base.baseapplication.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.base.baseapplication.eventbus.PermissionsResultEvent
import dagger.android.support.DaggerFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment : DaggerFragment() {

    private lateinit var mainView: View
    private lateinit var baseActivity: BaseActivity

    protected abstract fun getContentViewResource(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(getContentViewResource(), container, false)
        return mainView
    }

    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        baseActivity = activity as BaseActivity
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EventBus.getDefault().post(PermissionsResultEvent(requestCode, permissions, grantResults))
    }

    fun isNetworkConnected(): Boolean {
        return baseActivity.isNetworkConnected()
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    fun showToastMessage(@StringRes resId: Int) {
        showToastMessage(getString(resId))
    }

    fun showToastMessage(message: String?) {
        baseActivity.showToastMessage(message)
    }

    fun showSnackBarMessage(@StringRes resId: Int, layout: View) {
        showSnackBarMessage(getString(resId), layout)
    }

    fun showSnackBarMessage(message: String, layout: View) {
        baseActivity.showSnackBarMessage(message, layout)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: PermissionsResultEvent) {}

}