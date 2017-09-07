package com.dynamicheart.raven.data.model.raven.optionpoll

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by jianbangyang on 2017/9/5.
 *
 */
open class OptionPoll: RealmObject(){
    var type: Int? = null
    var question: String? = null
    var options: RealmList<Selection>? = null
}