package com.base.baseapplication.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.base.baseapplication.R
import com.base.baseapplication.eventbus.PermissionsResultEvent
import com.base.baseapplication.util.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : DaggerAppCompatActivity() {

    protected abstract fun getContentViewResource() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewResource())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EventBus.getDefault().post(PermissionsResultEvent(requestCode, permissions, grantResults))
    }

    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(this)
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun requestPermission(permissions: Array<String>, permissionsCode: Int) {
        ActivityCompat.requestPermissions(this, permissions, permissionsCode)
    }

    fun showToastMessage(@StringRes resId: Int) {
        showToastMessage(getString(resId))
    }

    fun showToastMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show()
        }
    }

    fun showSnackBarMessage(message: String, layout: View, color: Int) {
        val snackBar = Snackbar.make(layout, message, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, color))
        snackBar.show()
    }

    fun showSnackBarMessage(message: String, layout: View) {
        val snackBar = Snackbar.make(layout, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    fun showSnackBarMessage(@StringRes resId: Int, layout: View) {
        showSnackBarMessage(getString(resId), layout, android.R.color.holo_orange_light)
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
