package com.dynamicheart.raven.ui.createhouse

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class CreateHousePresenter
@Inject constructor(private val dataManager: DataManager): CreateHouseContract.Presenter(){

    override fun createHouse(createHouseForm: CreateHouseForm) {
        dataManager.createHouse(createHouseForm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {

                        },
                        onError = {
                            view.showError(it.message?:"")
                        }
                )
    }
}