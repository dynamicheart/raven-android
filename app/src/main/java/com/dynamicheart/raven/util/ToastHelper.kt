package com.dynamicheart.raven.util

import android.content.Context
import android.widget.Toast
import com.dynamicheart.raven.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastHelper
@Inject constructor(@ApplicationContext private val context: Context) {

    fun showShortToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showShortToast(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun showLongToast(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }
}