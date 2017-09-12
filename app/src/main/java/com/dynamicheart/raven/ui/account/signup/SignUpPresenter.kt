package com.dynamicheart.raven.ui.account.signup

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.model.user.form.CreateUserForm
import com.dynamicheart.raven.data.model.user.form.LoginForm
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class SignUpPresenter
@Inject constructor(private val accountManager: AccountManager,
                    private val toastHelper: ToastHelper) : SignUpContract.Presenter() {
    override fun signUp(createUserForm: CreateUserForm) {
        accountManager.createUser(createUserForm)
                .flatMap {
                    val loginForm = LoginForm(
                            createUserForm.username,
                            createUserForm.password
                    )
                    accountManager.fetchToken(loginForm)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            (view as SignUpFragment).goBackToPreviousActivity()
                        },
                        onError = {
                            toastHelper.showShortToast(it.message ?: "未知错误")
                        }

                )
    }

    override fun showPasswordInconsistentError() {
        toastHelper.showShortToast(R.string.message_password_inconsistent)
    }
}