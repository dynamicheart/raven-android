package com.dynamicheart.raven

import android.support.multidex.MultiDexApplication
import com.avos.avoscloud.AVAnalytics
import com.avos.avoscloud.AVOSCloud
import com.dynamicheart.raven.data.LeanCloudManager
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

        initLeanCloudService()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private fun initLeanCloudService(){
        AVOSCloud.initialize(this, LeanCloudManager.LEAN_CLOUD_APP_ID, LeanCloudManager.LEAN_CLOUD_APP_KEY)
        AVOSCloud.setDebugLogEnabled(true)
        AVOSCloud.setLastModifyEnabled(true)
        AVAnalytics.enableCrashReport(this, true)
    }
}