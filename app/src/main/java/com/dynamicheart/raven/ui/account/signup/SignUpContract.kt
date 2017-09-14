package com.dynamicheart.raven.ui.account.signup

import com.dynamicheart.raven.data.model.user.form.CreateUserForm
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object SignUpContract {
    interface View:MvpView{
        fun goBackToPreviousActivity()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun signUp(createUserForm: CreateUserForm)
        abstract fun showPasswordInconsistentError()
    }
}