package com.dynamicheart.raven.ui.confer

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.avos.avoscloud.im.v2.AVIMMessage
import com.dynamicheart.raven.data.LeanCloudManager
import com.dynamicheart.raven.util.leancloud.widget.LeftChatTextViewHolder
import com.dynamicheart.raven.util.leancloud.widget.RightChatTextViewHolder
import java.util.*
import javax.inject.Inject

class ImMessagesAdapter
@Inject constructor(private val leanCloudManager: LeanCloudManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @JvmStatic private val ITEM_LEFT_TEXT = 0
        @JvmStatic private val ITEM_RIGHT_TEXT = 1

        @JvmStatic private val TIME_INTERVAL = (10 * 60 * 1000).toLong()
    }

    private val messageList = ArrayList<AVIMMessage>()

    fun setMessageList(messages: List<AVIMMessage>?) {
        messageList.clear()
        if (null != messages) {
            messageList.addAll(messages)
        }
    }

    fun addMessageList(messages: List<AVIMMessage>) {
        messageList.addAll(0, messages)
    }

    fun addMessage(message: AVIMMessage) {
        messageList.addAll(Arrays.asList(message))
    }

    val firstMessage: AVIMMessage?
        get() = if (messageList.size > 0) {
            messageList[0]
        } else {
            null
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            ITEM_LEFT_TEXT -> LeftChatTextViewHolder(parent.context, parent)
            ITEM_RIGHT_TEXT -> RightChatTextViewHolder(parent.context, parent)
            else -> null
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftChatTextViewHolder) {
            holder.bindData(messageList[position])
            holder.showTimeView(shouldShowTime(position))
        } else if (holder is RightChatTextViewHolder){
            holder.bindData(messageList[position])
            holder.showTimeView(shouldShowTime(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return if (message.from == leanCloudManager.avImClient.clientId) {
            ITEM_RIGHT_TEXT
        } else {
            ITEM_LEFT_TEXT
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    private fun shouldShowTime(position: Int): Boolean {
        if (position == 0) {
            return true
        }
        val lastTime = messageList[position - 1].timestamp
        val curTime = messageList[position].timestamp
        return curTime - lastTime > TIME_INTERVAL
    }

}