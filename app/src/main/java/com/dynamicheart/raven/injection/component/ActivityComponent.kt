package com.dynamicheart.raven.injection.component

import com.dynamicheart.raven.injection.PerActivity
import com.dynamicheart.raven.injection.module.ActivityModule
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import com.dynamicheart.raven.ui.confer.ConferActivity
import com.dynamicheart.raven.ui.createhouse.CreateHouseActivity
import com.dynamicheart.raven.ui.draft.DraftActivity
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import com.dynamicheart.raven.ui.main.MainActivity
import com.dynamicheart.raven.ui.user.UserActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(authenticatorActivity: AuthenticatorActivity)
    fun inject(draftActivity: DraftActivity)
    fun inject(conferActivity: ConferActivity)
    fun inject(createHouseActivity: CreateHouseActivity)
    fun inject(houseDetailActivity: HouseDetailActivity)
    fun inject(userActivity: UserActivity)
}