package com.dynamicheart.raven.ui.housedetail

import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.main.house.HouseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class HouseDetailPresenter
@Inject constructor(private val dataManager: DataManager,
                    private val inMemoryDatabaseHelper: InMemoryDatabaseHelper) : HouseDetailContract.Presenter() {

    private var house: House? = null

    override fun detachView() {
        super.detachView()
        inMemoryDatabaseHelper.delete(HouseDetailActivity::class.java.name)
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadHouse(index: Int) {
        val houses = inMemoryDatabaseHelper.get(HouseFragment::class.java.name, HouseFragment.IN_MEMORY_DB_HOUSE_LIST)
        if (houses != null) {
            val currentHouse = (houses as List<House>)[index]
            inMemoryDatabaseHelper.store(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE, currentHouse)
            house = currentHouse
            view.showHouse(currentHouse)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadHouse() {
        val currentHouse = inMemoryDatabaseHelper.get(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE)
        if (currentHouse != null) {
            house = currentHouse as House
            view.showHouse(currentHouse)
        }
    }

    override fun leaveHouse() {
        val houseId = house?.id
        if (houseId != null) {
            dataManager.leaveHouse(houseId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                view.showLeaveHouseSuccess()
                            },
                            onError = {

                            }
                    )
        }
    }
}