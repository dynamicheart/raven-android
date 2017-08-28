package com.dynamicheart.courier.injection.component

import android.app.Application
import android.content.Context
import com.dynamicheart.courier.data.local.PreferencesHelper
import com.dynamicheart.courier.injection.ApplicationContext
import com.dynamicheart.courier.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dynamicheart on 20/8/2017.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ApplicationContext fun context(): Context
    fun application(): Application
    fun preferencesHelper(): PreferencesHelper
}