package com.dynamicheart.raven.injection.component

import android.app.Application
import android.content.Context
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.PreferencesHelper
import com.dynamicheart.raven.injection.ApplicationContext
import com.dynamicheart.raven.injection.module.ApplicationModule
import com.dynamicheart.raven.injection.module.DataModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dynamicheart on 20/8/2017.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface ApplicationComponent {

    @ApplicationContext fun context(): Context
    fun application(): Application
    fun preferencesHelper(): PreferencesHelper
    fun dataManger(): DataManager
}