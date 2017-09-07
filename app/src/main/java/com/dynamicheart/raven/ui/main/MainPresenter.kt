package com.dynamicheart.raven.ui.main

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscription

import javax.inject.Inject

/**
 * Created by dynamicheart on 20/8/2017.
 */
@ConfigPersistent
class MainPresenter
@Inject constructor(private val dataManager : DataManager) : MainContract.Presenter(){

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }
}