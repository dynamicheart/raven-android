package com.dynamicheart.raven.ui.createhouse

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class CreateHousePresenter
@Inject constructor(private val dataManager: DataManager,
                    private val inMemoryDatabaseHelper: InMemoryDatabaseHelper): CreateHouseContract.Presenter(){

    override fun createHouse(createHouseForm: CreateHouseForm) {
        dataManager.createHouse(createHouseForm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            inMemoryDatabaseHelper.store(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE, it)
                            view.goToHouseDetailActivity()
                        },
                        onError = {
                            view.showError(it.message?:"")
                        }
                )
    }
}