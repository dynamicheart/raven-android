package com.dynamicheart.raven.injection.component

import com.dynamicheart.raven.injection.PerActivity
import com.dynamicheart.raven.injection.module.ActivityModule
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import com.dynamicheart.raven.ui.confer.ConferActivity
import com.dynamicheart.raven.ui.createhouse.CreateHouseActivity
import com.dynamicheart.raven.ui.draft.DraftActivity
import com.dynamicheart.raven.ui.findhouse.FindHouseActivity
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import com.dynamicheart.raven.ui.inraven.InRavenActivity
import com.dynamicheart.raven.ui.main.MainActivity
import com.dynamicheart.raven.ui.member.MemberActivity
import com.dynamicheart.raven.ui.memberlist.MemberListActivity
import com.dynamicheart.raven.ui.raven.RavenActivity
import com.dynamicheart.raven.ui.select.SelectActivity
import com.dynamicheart.raven.ui.serve.ServeActivity
import com.dynamicheart.raven.ui.updatehouse.UpdateHouseActivity
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
    fun inject(findHouseActivity: FindHouseActivity)
    fun inject(selectActivity: SelectActivity)
    fun inject(inRavenActivity: InRavenActivity)
    fun inject(memberListActivity: MemberListActivity)
    fun inject(updateHouseActivity: UpdateHouseActivity)
    fun inject(memberActivity: MemberActivity)
    fun inject(serveActivity: ServeActivity)
    fun inject(ravenActivity: RavenActivity)
}