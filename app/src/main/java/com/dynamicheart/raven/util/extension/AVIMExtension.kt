package com.dynamicheart.raven.util.extension

import com.avos.avoscloud.im.v2.*
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback
import com.avos.avoscloud.im.v2.callback.AVIMMessagesQueryCallback
import io.reactivex.Observable

fun AVIMConversation.rxQueryMessages(): Observable<List<AVIMMessage>> {
    return Observable.create<List<AVIMMessage>>({ emitter ->
        this.queryMessages(object : AVIMMessagesQueryCallback() {
            override fun done(list: List<AVIMMessage>, e: AVIMException?) {
                if (e == null) {
                    emitter.onNext(list)
                    emitter.onComplete()
                } else {
                    emitter.onError(e)
                }
            }
        })
    })
}

fun AVIMConversation.rxQueryMessages(msgId: String?, timestamp: Long, limit: Int): Observable<List<AVIMMessage>> {
    return Observable.create<List<AVIMMessage>>({ emitter ->
        this.queryMessages(msgId, timestamp, limit, object : AVIMMessagesQueryCallback() {
            override fun done(list: List<AVIMMessage>, e: AVIMException?) {
                if (e == null) {
                    emitter.onNext(list)
                    emitter.onComplete()
                } else {
                    emitter.onError(e)
                }
            }
        })
    })
}

fun AVIMClient.rxCreateConversation(members: ArrayList<String>, name: String?, attributes: Map<String, Any>?, isTransient: Boolean, isUnique: Boolean): Observable<AVIMConversation> {
    return Observable.create<AVIMConversation>({ emitter ->
        this.createConversation(members, name, attributes, isTransient, isUnique, object : AVIMConversationCreatedCallback() {
            override fun done(conversation: AVIMConversation, e: AVIMException?) {
                if(e == null){
                    emitter.onNext(conversation)
                    emitter.onComplete()
                }else{
                    emitter.onError(e)
                }
            }
        })
    })
}
