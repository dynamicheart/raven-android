package com.dynamicheart.raven.ui.select.selecthouse

import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.draft.DraftActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class SelectHousePresenter
@Inject constructor(private val dataManager: DataManager,
                    private val inMemoryDatabaseHelper: InMemoryDatabaseHelper): SelectHouseContract.Presenter() {

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
        inMemoryDatabaseHelper.delete(SelectHouseFragment::class.java.name)
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadHouses() {
        disposable?.dispose()
        val result = inMemoryDatabaseHelper.get(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_RULING_HOUSE_LIST)
        if(result == null){
            dataManager.getHouses()
                    .flatMapIterable { t -> t }
                    .filter({ t ->
                        val currentUserId = AccountManager.user?.id
                        if (currentUserId == t.founder?.id) return@filter true
                            t.members?.forEach {
                                Timber.i("Logging member %s, %s", currentUserId, it.userId)
                                if (it.userId == currentUserId && (it.role == 1 || it.role == 2)) return@filter true
                            }
                        false
                    })
                    .toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onSuccess = {
                                if(it.isEmpty()) view.showHousesEmpty() else view.showHouseList(it)
                                inMemoryDatabaseHelper.store(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_RULING_HOUSE_LIST, it)
                            },
                            onError = {
                                view.showError()
                            }
                    )
        }else {
            val houses = result as List<House>
            if(houses.isEmpty()) view.showHousesEmpty() else view.showHouseList(houses)
        }

    }

    override fun recordSelectedHouse(index: Int) {
        inMemoryDatabaseHelper.store(SelectHouseFragment::class.java.name, SelectHouseFragment.IN_MEMORY_DB_SELECTED_HOUSE_INDEX, index)
    }
}