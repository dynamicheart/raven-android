package com.dynamicheart.raven.ui.select.selectmember

import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.draft.DraftActivity
import com.dynamicheart.raven.ui.select.selecthouse.SelectHouseFragment
import javax.inject.Inject

@ConfigPersistent
class SelectMemberPresenter
@Inject constructor(private val inMemoryDatabaseHelper: InMemoryDatabaseHelper) : SelectMemberContract.Presenter() {

    var currentHouse: House? = null

    @Suppress("UNCHECKED_CAST")
    override fun loadMembers() {
        val index = inMemoryDatabaseHelper.get(SelectHouseFragment::class.java.name, SelectHouseFragment.IN_MEMORY_DB_SELECTED_HOUSE_INDEX) as Int
        val houses = inMemoryDatabaseHelper.get(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_RULING_HOUSE_LIST) as List<House>
        val house = houses[index]
        currentHouse = house
        val members = house.members
        if (members != null) {
            view.showMembers(members)
        }
    }

    override fun saveSelection(members: List<Member>, selection: MutableMap<String, Boolean>) {
        val selectedMemberIds = ArrayList<String>()
        selection.forEach {
            if (it.value) {
                selectedMemberIds.add(it.key)
            }
        }
        inMemoryDatabaseHelper.store(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_SELECTED_HOUSE, currentHouse?.id ?: "")
        inMemoryDatabaseHelper.store(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_SELECTED_MEMBERS, selectedMemberIds)
    }
}