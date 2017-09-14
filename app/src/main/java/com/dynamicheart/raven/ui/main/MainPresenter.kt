package com.dynamicheart.raven.ui.main

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.LeanCloudManager
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

@ConfigPersistent
class MainPresenter
@Inject constructor(private val dataManager: DataManager,
                    private val accountManager: AccountManager,
                    private val leanCloudManager: LeanCloudManager,
                    private val toastHelper: ToastHelper) : MainContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun loadUser() {
        view.populateNavHeader(AccountManager.user)
    }
}