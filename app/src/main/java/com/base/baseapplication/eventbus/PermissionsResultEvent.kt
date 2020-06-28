package com.base.baseapplication.eventbus

class PermissionsResultEvent(var requestCode: Int, var permissions: Array<String>, var grantResults: IntArray)
