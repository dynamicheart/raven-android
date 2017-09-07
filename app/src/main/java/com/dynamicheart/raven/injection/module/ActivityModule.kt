package com.dynamicheart.raven.injection.module

import android.app.Activity
import android.content.Context
import com.dynamicheart.raven.injection.ActivityContext
import com.dynamicheart.raven.injection.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by dynamicheart on 20/8/2017.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    @ActivityContext
    fun providesContext(): Context {
        return activity
    }
}