package com.dynamicheart.raven.ui.inraven

import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.local.InMemoryDatabaseHelper
import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.data.model.reply.form.CreateReplyForm
import com.dynamicheart.raven.data.model.whistleblowing.form.CreateWhistleBlowingForm
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.ui.main.inbox.InboxFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class InRavenPresenter
@Inject constructor(private val dataManager: DataManager,
                    private val inMemoryDatabaseHelper: InMemoryDatabaseHelper) : InRavenContract.Presenter() {

    private var disposable: Disposable? = null
    private var inRaven: InRaven? = null

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadInRavenFromLocal(index: Int) {
        val result = inMemoryDatabaseHelper.get(InboxFragment::class.java.name, InboxFragment.IN_MEMORY_DB_IN_RAVEN_LIST)
        if (result != null) {
            val inRavens = result as List<InRaven>
            inRaven = inRavens[index]
            view.showInRaven(inRavens[index])
        }
    }

    override fun loadInRavenFromRemote(id: String) {
        disposable?.dispose()
        disposable = dataManager.getInRaven(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            inRaven = it
                            view.showInRaven(it)
                        },
                        onError = {
                            view.showToast(R.string.inraven_none)
                        }
                )
    }


    override fun sendReply() {
        disposable?.dispose()
        val ravenId = inRaven?.id
        if (ravenId != null) {
            disposable = dataManager.sendReply(ravenId,
                    CreateReplyForm(
                            ravenId,
                            AccountManager.user?.id,
                            null,
                            null
                    ))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                view.showToast(R.string.send_reply_success)
                                (view as InRavenActivity).finish()
                            },
                            onError = {
                                view.showToast(R.string.send_reply_success)
                                (view as InRavenActivity).finish()
                            }
                    )
        }

    }

    override fun sendWhistleBlowing(description: String) {
        disposable?.dispose()

        disposable = dataManager.sendWhistleBlowing(
                CreateWhistleBlowingForm(
                        inRaven?.id,
                        0,
                        description
                ))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            view.showToast(R.string.whisle_blowing_success)
                        },
                        onError = {
                            view.showToast(R.string.whisle_blowing_success)
                        }
                )
    }
}