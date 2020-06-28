package com.base.baseapplication.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.base.baseapplication.eventbus.PermissionsResultEvent


class PermissionUtil {

    companion object {

        val LOCATION_PERMISSION_REQUEST_CODE = 22
        val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 23
        val WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 24

        fun checkLocationPermission(activity: Activity): Boolean {
            return if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                false
            } else {
                true
            }
        }

        fun checkReadExternalStoragePermission(activity: Activity): Boolean {
            return if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
                false
            } else {
                true
            }
        }

        fun checkWriteExternalStoragePermission(activity: Activity): Boolean {
            return if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
                false
            } else {
                true
            }
        }

        fun isPermissionGranted(event: PermissionsResultEvent) : Boolean {
            return (event.grantResults.isNotEmpty() && event.grantResults[0] == PackageManager.PERMISSION_GRANTED)
        }

    }

}

