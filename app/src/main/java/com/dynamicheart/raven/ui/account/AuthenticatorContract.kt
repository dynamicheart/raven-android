package com.dynamicheart.raven.ui.account

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object AuthenticatorContract {
    interface View: MvpView{
        fun showLoginFragment()
        fun showSignUpFragment()
        fun goBackToPreviousActivity()
    }

    abstract class Presenter: BasePresenter<View>(){
    }
}