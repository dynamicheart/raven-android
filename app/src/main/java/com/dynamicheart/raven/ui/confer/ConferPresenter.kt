package com.dynamicheart.raven.ui.confer

import com.avos.avoscloud.im.v2.AVIMConversation
import com.avos.avoscloud.im.v2.AVIMException
import com.dynamicheart.raven.data.LeanCloudManager
import com.dynamicheart.raven.data.model.ref.UserRef
import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.util.extension.rxCreateConversation
import com.dynamicheart.raven.util.extension.rxQueryMessages
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@ConfigPersistent
class ConferPresenter
@Inject constructor(private val leanCloudManager: LeanCloudManager) : ConferContract.Presenter() {

    companion object {
        @JvmStatic val EXTRA_MEMBER_USER_ID = "EXTRA_MEMBER_USER_ID"
    }

    private var imConversation: AVIMConversation? = null

    override fun fetchMessage() {
        imConversation?.rxQueryMessages()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribeBy(
                        onNext = {
                            view.stopRefreshing()
                            view.showMessages(it)
                        },
                        onError = {
                            view.stopRefreshing()
                        }
                )
    }

    override fun reFetchMessage(adapter: ImMessagesAdapter) {
        val message = adapter.firstMessage
        imConversation?.rxQueryMessages(message?.messageId, message!!.timestamp, 20)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribeBy(
                        onNext = {
                            view.showRefreshedMessages(it)
                        }
                )
    }

    override fun getConversation() {
        val userId = (view as ConferActivity).intent.getStringExtra(EXTRA_MEMBER_USER_ID)
        val client = leanCloudManager.avImClient
        client.rxCreateConversation(arrayListOf(userId), null, HashMap<String, Any>(), false, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            imConversation = it
                            view.showConversation(it)

                        },
                        onError = {

                        })

    }
}