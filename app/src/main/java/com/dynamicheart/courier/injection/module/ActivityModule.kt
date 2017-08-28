package com.dynamicheart.courier.injection.module

import android.app.Activity
import android.content.Context
import com.dynamicheart.courier.injection.ActivityContext
import com.dynamicheart.courier.injection.PerActivity
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