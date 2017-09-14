package com.dynamicheart.raven.ui.select.selectmember

import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object SelectMemberContract {
    interface View: MvpView{
        fun showMembers(members: List<Member>)
        fun showMembersEmpty()
        fun showError()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadMembers()
        abstract fun saveSelection(members: List<Member>, selection: MutableMap<String, Boolean>)
    }
}