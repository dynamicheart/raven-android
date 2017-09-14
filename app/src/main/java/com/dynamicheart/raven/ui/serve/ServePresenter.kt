package com.dynamicheart.raven.ui.serve

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class ServePresenter
@Inject constructor(private val dataManager: DataManager,
                    private val inMemoryDatabaseHelper: InMemoryDatabaseHelper) : ServeContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    @Suppress("UNCHECKED_CAST")
    override fun fetchServes() {
        val result = inMemoryDatabaseHelper.get(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE)
        if (result != null) {
            val currentHouse = result as House
            val houseId = currentHouse.id
            if (houseId != null) {
                disposable?.dispose()
                dataManager.getServeApply(houseId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    if(it.isEmpty())view.showServesEmpty() else view.showServes(it)
                                },
                                onError = {
                                    view.showToast(R.string.load_serve_fail)
                                }
                        )
            }
        }
    }

}