package com.dynamicheart.raven

import android.app.Application
import com.avos.avoscloud.AVInstallation
import com.avos.avoscloud.SaveCallback
import com.dynamicheart.raven.injection.component.ApplicationComponent
import com.dynamicheart.raven.injection.component.DaggerApplicationComponent
import com.dynamicheart.raven.injection.module.ApplicationModule
import timber.log.Timber
import com.avos.avoscloud.AVException



/**
 * Created by dynamicheart on 16/8/2017.
 */
class RavenApplication : Application() {

    companion object {
        @JvmStatic private val LEAN_CLOUD_APP_ID = ""
        @JvmStatic private val LEAN_CLOUD_APP_KEY = ""
    }

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