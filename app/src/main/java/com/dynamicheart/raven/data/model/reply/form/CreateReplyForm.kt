package com.dynamicheart.raven.data.model.reply.form

import com.dynamicheart.raven.data.model.reply.Poll.Poll

data class CreateReplyForm(
        var ravenId: String?,
        var userId: String?,
        var polls: List<Poll>?,
        var content: String?
)