package com.dynamicheart.raven.ui.select

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object SelectContract {
    interface View: MvpView{
        fun showSelectHouseFragment()
        fun showSelectMemberFragment()
        fun goBackToPreviousActivity()
    }

    abstract class Presenter: BasePresenter<View>(){

    }
}