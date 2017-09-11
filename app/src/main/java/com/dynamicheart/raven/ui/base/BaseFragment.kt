package com.dynamicheart.raven.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.dynamicheart.raven.RavenApplication
import com.dynamicheart.raven.injection.component.FragmentComponent
import com.dynamicheart.raven.injection.component.ConfigPersistentComponent
import com.dynamicheart.raven.injection.component.DaggerConfigPersistentComponent
import com.dynamicheart.raven.injection.module.FragmentModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

open class BaseFragment : Fragment() {

    companion object {
        @JvmStatic private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        @JvmStatic private val NEXT_ID = AtomicLong(0)
        @JvmStatic private val componentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

    private var fragmentId: Long = 0
    lateinit var fragmentComponent: FragmentComponent
        get

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        fragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()

        if (componentsMap[fragmentId] != null)
            Timber.i("Reusing ConfigPersistentComponent id=%d", fragmentId)

        val configPersistentComponent = componentsMap.getOrPut(fragmentId, {
            Timber.i("Creating new ConfigPersistentComponent id=%d", fragmentId)

            val component = (activity.application as RavenApplication).applicationComponent

            DaggerConfigPersistentComponent.builder()
                    .applicationComponent(component)
                    .build()
        })

        fragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
    }
}