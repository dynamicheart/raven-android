package com.dynamicheart.raven

import android.app.Application
import com.dynamicheart.raven.injection.component.ApplicationComponent
import com.dynamicheart.raven.injection.component.DaggerApplicationComponent
import com.dynamicheart.raven.injection.module.ApplicationModule
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