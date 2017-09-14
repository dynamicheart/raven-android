package com.dynamicheart.raven.ui.main.outbox

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class OutboxPresenter
@Inject constructor(private val dataManager: DataManager) : OutboxContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun loadRavens() {
        disposable?.dispose()
        disposable = dataManager.getRavens()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            if(it.isEmpty())view.showRavensEmpty() else view.showRavens(it)
                        },
                        onError = {
                            view.showError()
                        }
                )
    }
}