package com.dynamicheart.raven.util.eventbus.im

import com.avos.avoscloud.im.v2.AVIMConversation
import com.avos.avoscloud.im.v2.AVIMTypedMessage

class ImTypeMessageEvent {
    var message: AVIMTypedMessage? = null
    var conversation: AVIMConversation? = null
}
