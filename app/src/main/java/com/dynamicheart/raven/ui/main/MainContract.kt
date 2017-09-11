package com.dynamicheart.raven.ui.main

import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object MainContract {

    interface View: MvpView {
        fun populateNavHeader(user: User?)
    }

    abstract class Presenter: BasePresenter<View>() {
        abstract fun registerInstallation()
        abstract fun loadUser()
    }
}