package com.dynamicheart.raven.ui.main.inbox

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class InboxPresenter
@Inject constructor(private val dataManager: DataManager) : InboxContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun loadInRavens() {
        disposable?.dispose()
        disposable = dataManager.syncAndGetInRavens()
                .onErrorResumeNext { _: Throwable ->
                    return@onErrorResumeNext dataManager.syncAndGetInRavens()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            if (it.isEmpty()) view.showInRavensEmpty() else view.showInRavens(it)
                        },
                        onError = {
                            Timber.e(it, "There was an error loading the inRavens.")
                            view.showError()
                        }
                )
    }
}