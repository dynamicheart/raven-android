package com.dynamicheart.raven.injection.component

import com.dynamicheart.raven.injection.PerActivity
import com.dynamicheart.raven.injection.module.ActivityModule
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import com.dynamicheart.raven.ui.draft.DraftActivity
import com.dynamicheart.raven.ui.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(authenticatorActivity: AuthenticatorActivity)
    fun inject(draftActivity: DraftActivity)
}