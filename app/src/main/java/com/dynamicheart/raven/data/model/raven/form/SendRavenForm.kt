package com.dynamicheart.raven.data.model.raven.form

import com.dynamicheart.raven.data.model.raven.optionpoll.OptionPoll

data class SendRavenForm(
        var houseId: String?,
        var addresserId: String?,
        var addresseeIds: List<String>?,
        var title: String?,
        var description: String?,
        var type: Int?,
        var optionPolls: List<OptionPoll>?,
        var attachmentId: String?,
        var mold: Boolean = false,
        var moldId: String?
)