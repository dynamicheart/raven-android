package com.dynamicheart.raven.data.model.reply

import com.dynamicheart.raven.data.model.reply.Poll.Poll

data class Reply(
        var id: String?,
        var userId: String?,
        var polls: List<Poll>?
)