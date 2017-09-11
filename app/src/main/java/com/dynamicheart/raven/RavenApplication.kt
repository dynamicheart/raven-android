package com.dynamicheart.raven

import android.support.multidex.MultiDexApplication
import com.dynamicheart.raven.injection.component.ApplicationComponent
import com.dynamicheart.raven.injection.component.DaggerApplicationComponent
import com.dynamicheart.raven.injection.module.ApplicationModule
import timber.log.Timber

class RavenApplication : MultiDexApplication() {

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