package com.dynamicheart.courier.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by dynamicheart on 16/8/2017.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    companion object {
        @JvmStatic private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        @JvmStatic private val NEXT_ID = AtomicLong(0)
    }

    private var activityId: Long = 0
    private var activityComponent = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}