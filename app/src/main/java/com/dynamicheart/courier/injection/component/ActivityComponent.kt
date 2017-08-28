package com.dynamicheart.courier.injection.component

import com.dynamicheart.courier.injection.PerActivity
import com.dynamicheart.courier.injection.module.ActivityModule
import com.dynamicheart.courier.ui.main.MainActivity
import dagger.Subcomponent

/**
 * Created by dynamicheart on 21/8/2017.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}