package com.dynamicheart.courier

import android.app.Application
import android.content.Context
import com.dynamicheart.courier.injection.component.ApplicationComponent
import com.dynamicheart.courier.injection.component.DaggerApplicationComponent
import com.dynamicheart.courier.injection.module.ApplicationModule
import timber.log.Timber

/**
 * Created by dynamicheart on 16/8/2017.
 */
class RavenApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}