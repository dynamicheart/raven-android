package com.dynamicheart.courier.injection.module

import android.app.Application
import android.content.Context
import com.dynamicheart.courier.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dynamicheart on 18/8/2017.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }


}