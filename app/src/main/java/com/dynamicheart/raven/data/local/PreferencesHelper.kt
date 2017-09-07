package com.dynamicheart.raven.data.local

import android.content.Context
import android.content.SharedPreferences
import com.dynamicheart.raven.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by dynamicheart on 20/8/2017.
 */
@Singleton
class PreferencesHelper
@Inject constructor(@ApplicationContext context: Context) {

    companion object {
        @JvmStatic private val PREF_FILE_NAME = "raven_pref_file"
    }

    val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        pref.edit().clear().apply()
    }
}