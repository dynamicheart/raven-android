package com.dynamicheart.raven.ui.confer

import com.avos.avoscloud.im.v2.AVIMConversation
import com.avos.avoscloud.im.v2.AVIMMessage
import com.dynamicheart.raven.data.model.ref.UserRef
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object ConferContract {
    interface View: MvpView{
        fun showConversation(conversation: AVIMConversation)
        fun showMessages(list: List<AVIMMessage>)
        fun showRefreshedMessages(list: List<AVIMMessage>)
        fun stopRefreshing()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun fetchMessage()
        abstract fun reFetchMessage(adapter: ImMessagesAdapter)
        abstract fun getConversation()
    }
}