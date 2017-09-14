package com.dynamicheart.raven.data.model.house

import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.data.model.ref.UserRef
import java.util.*

data class House(
        var id: String?,
        var name: String?,
        var description: String?,
        var status: Int?,
        var capacity: Int?,
        var memberNumbers: Int?,
        var sigil: String?,
        var publicity: Boolean?,
        var tags: Set<String>?,
        var founder: UserRef?,
        var members: List<Member>?,
        var createdDate: Date?
)