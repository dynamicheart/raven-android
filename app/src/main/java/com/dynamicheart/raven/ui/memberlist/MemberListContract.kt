package com.dynamicheart.raven.ui.memberlist

import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object MemberListContract {
    interface View: MvpView{
        fun showMembers(members: List<Member>)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadMembers()
    }
}