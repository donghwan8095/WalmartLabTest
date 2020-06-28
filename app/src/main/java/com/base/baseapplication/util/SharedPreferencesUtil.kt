package com.base.baseapplication.util

import android.content.Context
import android.content.SharedPreferences
import com.base.baseapplication.R

object SharedPreferencesUtil {

    lateinit var pref : SharedPreferences

    fun saveStringData(context: Context, key: String, data: String) {
        pref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (pref.edit()) {
            putString(key, data)
            commit()
        }
    }

    fun getStringData(context: Context, key: String, defaultData: String? = null) : String? {
        pref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        return pref.getString(key,defaultData)
    }

    fun deleteData(context: Context, key: String) {
        pref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (pref.edit()) {
            remove(key)
            commit()
        }
    }

}