package com.dynamicheart.raven.ui.user

import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object UserContract {
    interface View: MvpView{
        fun showUserInfo(user: User?)
        fun showCheckoutError()
        fun restartApplication()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadUser()
        abstract fun logout()
    }
}