package com.dynamicheart.raven.ui.memberlist

import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import javax.inject.Inject

@ConfigPersistent
class MemberListPresenter
@Inject constructor(private val inMemoryDatabaseHelper: InMemoryDatabaseHelper): MemberListContract.Presenter() {

    @Suppress("UNCHECKED_CAST")
    override fun loadMembers() {
        val result = inMemoryDatabaseHelper.get(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE)
        if(result != null){
            val currentHouse = result as House
            val members = currentHouse.members
            if (members != null){
                view.showMembers(members)
            }
        }
    }
}