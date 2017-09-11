package com.dynamicheart.raven.ui.account.login

import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.model.user.form.LoginForm
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class LoginPresenter
@Inject constructor(private val accountManager: AccountManager,
                    private val toastHelper: ToastHelper) : LoginContract.Presenter() {
    override fun login(loginForm: LoginForm) {
        accountManager.fetchToken(loginForm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            accountManager.syncUser()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeBy(
                                            onNext = {
                                                (view as LoginFragment).goBackToPreviousActivity()
                                            },
                                            onError = {
                                                toastHelper.showShortToast(it.message ?: "未知错误")
                                            }
                                    )
                        },
                        onError = {
                            toastHelper.showShortToast(it.message ?: "未知错误")
                        }
                )
    }
}