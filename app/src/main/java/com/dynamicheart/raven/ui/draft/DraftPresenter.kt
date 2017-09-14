package com.dynamicheart.raven.ui.draft

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.raven.form.SendRavenForm
import com.dynamicheart.raven.injection.ConfigPersistent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class DraftPresenter
@Inject constructor(private val inMemoryDatabaseHelper: InMemoryDatabaseHelper,
                    private val dataManager: DataManager) : DraftContract.Presenter() {

    override fun detachView() {
        super.detachView()
        inMemoryDatabaseHelper.delete(DraftActivity::class.java.name)
    }

    @Suppress("UNCHECKED_CAST")
    override fun sendRaven(title: String, content: String) {
        val selectedHouseId = inMemoryDatabaseHelper.get(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_SELECTED_HOUSE) as String
        val selectedMemberIds = inMemoryDatabaseHelper.get(DraftActivity::class.java.name, DraftActivity.IN_MEMORY_DB_SELECTED_MEMBERS) as List<String>
        dataManager.sendRaven(SendRavenForm(
                selectedHouseId,
                AccountManager.user?.id,
                selectedMemberIds,
                title,
                content,
                0,
                null,
                null,
                false,
                null)
        ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            view.showToast(R.string.send_raven_success)
                            (view as DraftActivity).finish()
                        },
                        onError = {
                            view.showToast(R.string.send_raven_error)
                        }
                )
    }
}
