package com.dynamicheart.raven.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dynamicheart.raven.RavenApplication
import com.dynamicheart.raven.injection.component.ActivityComponent
import com.dynamicheart.raven.injection.component.ConfigPersistentComponent
import com.dynamicheart.raven.injection.component.DaggerConfigPersistentComponent
import com.dynamicheart.raven.injection.module.ActivityModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    companion object {
        @JvmStatic private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        @JvmStatic private val NEXT_ID = AtomicLong(0)
        @JvmStatic private val componentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

    private var activityId: Long = 0
    lateinit var activityComponent: ActivityComponent
        get

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()

        if (componentsMap[activityId] != null)
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)

        val configPersistentComponent = componentsMap.getOrPut(activityId, {
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)

            val component = (applicationContext as RavenApplication).applicationComponent

            DaggerConfigPersistentComponent.builder()
                    .applicationComponent(component)
                    .build()
        })

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }
}