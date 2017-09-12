package com.dynamicheart.raven.ui.user

import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class UserPresenter
@Inject constructor(private val dateManager: AccountManager) : UserContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun loadUser() {
        view.showUserInfo(AccountManager.user)
    }

    override fun logout() {
        dateManager.checkout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onComplete = {
                            view.restartApplication()
                        },
                        onError = {
                            view.showCheckoutError()
                        }
                )
    }
}