package com.dynamicheart.raven.ui.main.house

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class HousePresenter
@Inject constructor(private val dataManager: DataManager) : HouseContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun fetchHouses() {
        disposable?.dispose()
        dataManager.getHouses()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            if (it.isEmpty()) view.showHousesEmpty() else view.showHouses(it)
                        },
                        onError = {
                            Timber.e(it, "There was an error loading the houses.")
                            view.showError()
                        })
    }
}