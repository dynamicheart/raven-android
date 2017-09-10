package com.dynamicheart.raven.injection.module

import android.app.Application
import android.content.Context
import com.dynamicheart.raven.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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