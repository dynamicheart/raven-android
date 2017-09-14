package com.dynamicheart.raven.ui.updatehouse

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.UpdateHouseForm
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class UpdateHousePresenter
@Inject constructor(private var dataManager: DataManager,
                    private var inMemoryDatabaseHelper: InMemoryDatabaseHelper) : UpdateHouseContract.Presenter() {

    private var disposable: Disposable? = null
    private var house: House? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun updateHouse(updateHouseForm: UpdateHouseForm) {
        disposable?.dispose()
        val houseId = house?.id
        if (houseId != null) {
            dataManager.updateHouse(houseId, updateHouseForm)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                view.showToast(R.string.update_house_success)
                            },
                            onError = {
                                view.showToast(R.string.update_house_fail)
                            }
                    )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadHouse() {
        val result = inMemoryDatabaseHelper.get(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE)
        if (result != null) {
            house = result as House
            view.showHouse(result)
        }
    }
}