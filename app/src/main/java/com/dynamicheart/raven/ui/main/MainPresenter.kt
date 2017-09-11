package com.dynamicheart.raven.ui.main

import android.app.Activity
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVInstallation
import com.avos.avoscloud.SaveCallback
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.remote.LeanCloudService
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

import javax.inject.Inject

@ConfigPersistent
class MainPresenter
@Inject constructor(private val dataManager: DataManager,
                    private val accountManager: AccountManager,
                    private val leanCloudService: LeanCloudService,
                    private val toastHelper: ToastHelper) : MainContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun registerInstallation() {
        leanCloudService.registerInstallation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            leanCloudService.uploadInstallation(it)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeBy(
                                            onNext = { leanCloudService.enableLeanCloudService() },
                                            onError = { toastHelper.showShortToast(R.string.message_installation_fail)}
                                    )
                        },
                        onError = { toastHelper.showShortToast(R.string.message_installation_fail) }
                )
    }

    override fun loadUser() {
        view.populateNavHeader(AccountManager.user)
    }
}