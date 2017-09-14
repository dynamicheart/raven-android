package com.dynamicheart.raven.data.model.serve

import com.dynamicheart.raven.data.model.ref.HouseRef
import com.dynamicheart.raven.data.model.ref.UserRef
import java.util.*

data class Serve(
        var id: String?,
        var house: HouseRef?,
        var man: UserRef?,
        var content: String?,
        var status: Int?,
        var type: Int?,
        var createdDate: Date?
)