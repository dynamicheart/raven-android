package com.dynamicheart.raven.ui.findhouse

import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.serve.form.CreateServeForm
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class FindHousePresenter
@Inject constructor(private val dataManager: DataManager,
                    private val inMemoryDatabaseHelper: InMemoryDatabaseHelper,
                    private val toastHelper: ToastHelper) : FindHouseContract.Presenter() {

    private var disposable: Disposable? = null
    private var house: House? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun searchHouse(id: String) {
        disposable?.dispose()
        if (id != "") {
            disposable = dataManager.findHouse(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                house = it
                                view.showResult(it)
                            },
                            onError = {
                            }
                    )
        } else {

        }
    }

    override fun serveHouse() {
        when (house?.publicity) {
            true -> {
                val houseId = house?.id
                if (houseId != null)
                    dataManager.joinPublicHouse(houseId)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribeBy(
                                    onNext = {
                                        toastHelper.showShortToast("加入成功")
                                    },
                                    onError = {
                                        toastHelper.showShortToast("加入失败")
                                    }
                            )
            }
            false -> {
                view.showCreateServeDialog()
            }
        }
    }

    override fun applyToServeHouse(content: String) {
        disposable?.dispose()
        disposable = dataManager.applyTOServePrivateHouse(CreateServeForm(AccountManager.user?.id, house?.id, content, 0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            toastHelper.showShortToast("加入成功")
                        },
                        onError = {
                            toastHelper.showShortToast("加入失败")
                        }
                )
    }
}