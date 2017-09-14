package com.dynamicheart.raven.ui.member

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class MemberPresenter
@Inject constructor(private val inMemoryDatabaseHelper: InMemoryDatabaseHelper,
                    private val dataManager: DataManager) : MemberContract.Presenter() {

    var currentHouse: House? = null
    var member: Member? = null

    private var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadMember(index: Int) {
        val house = inMemoryDatabaseHelper.get(HouseDetailActivity::class.java.name, HouseDetailActivity.IN_MEMORY_DB_CURRENT_HOUSE)
        if (house != null) {
            member = (house as House).members?.get(index)
            view.showMember(member, house)
        }
    }

    override fun expelMember(houseId: String?, userId: String?) {
        if (userId != null && houseId != null) {
            disposable?.dispose()
            dataManager.expelMember(houseId, userId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                view.showToast(R.string.expel_member_success)
                            },
                            onError = {
                                view.showToast(R.string.expel_member_fail)
                            }
                    )

        }
    }
}