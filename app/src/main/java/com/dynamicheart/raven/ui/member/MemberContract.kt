package com.dynamicheart.raven.ui.member

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView
object MemberContract {
    interface View: MvpView{
        fun showMember(member: Member?, house: House?)
        fun showToast(resId: Int)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadMember(index: Int)
        abstract fun expelMember(houseId: String?, userId: String?)
    }
}