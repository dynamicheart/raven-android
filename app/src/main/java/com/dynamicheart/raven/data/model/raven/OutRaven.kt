package com.dynamicheart.raven.data.model.raven

import com.dynamicheart.raven.data.model.raven.optionpoll.OptionPoll
import com.dynamicheart.raven.data.model.raven.ref.HouseRef
import com.dynamicheart.raven.data.model.raven.ref.UserRef

/**
 * Created by jianbangyang on 2017/9/5.
 *
 */
data class OutRaven(
        val id: String,
        val house: HouseRef,
        val addressee: List<UserRef>,
        val title: String,
        val description: String,
        val type: Int,
        val optionPolls: List<OptionPoll>
)