package com.dynamicheart.raven.data.model.reply

import com.dynamicheart.raven.data.model.reply.Poll.Poll

/**
 * Created by jianbangyang on 2017/9/5.
 *
 */
data class FeedBack(
        val id: String,
        val userId: String,
        val polls: List<Poll>
)