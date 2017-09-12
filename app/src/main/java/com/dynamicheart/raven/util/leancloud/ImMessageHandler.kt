package com.dynamicheart.raven.util.leancloud

import android.content.Context
import android.content.Intent
import com.avos.avoscloud.im.v2.AVIMClient
import com.avos.avoscloud.im.v2.AVIMConversation
import com.avos.avoscloud.im.v2.AVIMTypedMessage
import com.avos.avoscloud.im.v2.AVIMTypedMessageHandler
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.LeanCloudManager
import com.dynamicheart.raven.util.eventbus.im.ImTypeMessageEvent
import org.greenrobot.eventbus.EventBus

class ImMessageHandler(private val context: Context): AVIMTypedMessageHandler<AVIMTypedMessage>() {

    override fun onMessage(message: AVIMTypedMessage, conversation: AVIMConversation, client: AVIMClient) {

        var clientID = ""
        try {
            if (client.clientId == clientID) {

                // 过滤掉自己发的消息
                if (message.from != clientID) {
                    sendEvent(message, conversation)
//                    if (NotificationBoxUtils.isShowNotification(conversation.conversationId)) {
//                        sendNotification(message, conversation)
//                    }
                }
            } else {
                client.close(null)
            }
        } catch (e: IllegalStateException) {
            client.close(null)
        }

    }

    private fun sendEvent(message: AVIMTypedMessage, conversation: AVIMConversation?) {
        val event = ImTypeMessageEvent()
        event.message = message
        event.conversation = conversation
        EventBus.getDefault().post(event)
    }

    private fun sendNotification(message: AVIMTypedMessage, conversation: AVIMConversation) {
        val notificationContent = if (message is AVIMTextMessage)
            message.text
        else
            context.getString(R.string.chat_unsupport_message_type)

//        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
//        intent.putExtra(Constants.CONVERSATION_ID, conversation.conversationId)
//        intent.putExtra(Constants.MEMBER_ID, message.from)
//        NotificationBoxUtils.showNotification(context, "", notificationContent, null, intent)
    }
}