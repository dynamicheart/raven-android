package com.dynamicheart.raven.injection.component

import com.dynamicheart.raven.injection.PerFragment
import com.dynamicheart.raven.injection.module.FragmentModule
import com.dynamicheart.raven.ui.account.login.LoginFragment
import com.dynamicheart.raven.ui.account.signup.SignUpFragment
import com.dynamicheart.raven.ui.main.conferbox.ConferBoxFragment
import com.dynamicheart.raven.ui.main.house.HouseFragment
import com.dynamicheart.raven.ui.main.inbox.InboxFragment
import com.dynamicheart.raven.ui.main.outbox.OutboxFragment
import com.dynamicheart.raven.ui.select.selecthouse.SelectHouseFragment
import com.dynamicheart.raven.ui.select.selectmember.SelectMemberFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(inboxFragment: InboxFragment)
    fun inject(outboxFragment: OutboxFragment)
    fun inject(houseFragment: HouseFragment)
    fun inject(conferBoxFragment: ConferBoxFragment)
    fun inject(selectHouseFragment: SelectHouseFragment)
    fun inject(selectMemberFragment: SelectMemberFragment)
}