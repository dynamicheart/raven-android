package com.dynamicheart.raven.data.model.feedback

import com.dynamicheart.raven.data.model.feedback.Poll.Poll

/**
 * Created by jianbangyang on 2017/9/5.
 *
 */
data class FeedBack(
        val id: String,
        val userId: String,
        val polls: List<Poll>
)