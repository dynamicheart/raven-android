package com.dynamicheart.raven.ui.housedetail

import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.main.house.HouseFragment
import javax.inject.Inject

@ConfigPersistent
class HouseDetailPresenter
@Inject constructor(private val inMemoryDatabaseHelper: InMemoryDatabaseHelper): HouseDetailContract.Presenter() {

    @Suppress("UNCHECKED_CAST")
    override fun loadHouse(index: Int) {
        val houses = inMemoryDatabaseHelper.get(HouseFragment::class.java.name, HouseFragment.IN_MEMORY_DB_CURRENT_HOUSE)
        if(houses != null){
            val currentHouse = (houses as List<House>)[index]
            view.showHouse(currentHouse)
        }
    }
}