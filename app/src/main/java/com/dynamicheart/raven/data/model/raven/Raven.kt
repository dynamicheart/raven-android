package com.dynamicheart.raven.data.model.raven

import com.dynamicheart.raven.data.model.raven.optionpoll.OptionPoll
import com.dynamicheart.raven.data.model.ref.HouseRef
import com.dynamicheart.raven.data.model.ref.UserRef
import java.util.*

data class Raven(
        var id: String?,
        var house: HouseRef?,
        var addressee: List<UserRef>?,
        var title: String?,
        var description: String?,
        var type: Int?,
        var optionPolls: List<OptionPoll>?,
        var mold: Boolean? = false,
        var moldId: String?,
        var createdDate: Date?
)