package com.dynamicheart.raven.data.model.house

import java.util.*

/**
 * Created by jianbangyang on 2017/9/5.
 */
data class House(
        val id: String,
        val name: String,
        val description: String,
        val status: Int,
        val capacity: Int,
        val memberNumbers: Int,
        val tags: Set<String>,
        val createdDate: Date
)