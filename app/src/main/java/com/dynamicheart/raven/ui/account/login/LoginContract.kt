package com.dynamicheart.raven.ui.account.login

import com.dynamicheart.raven.data.model.user.form.LoginForm
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object LoginContract {
    interface View: MvpView {
        fun goBackToPreviousActivity()
    }

    abstract class Presenter: BasePresenter<View>() {
        abstract fun login(loginForm: LoginForm)
    }
}