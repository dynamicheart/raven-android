package com.dynamicheart.raven.data.model.raven.optionpoll

import com.dynamicheart.raven.util.realm.RealmString
import io.realm.RealmList
import io.realm.RealmObject

open class OptionPoll: RealmObject(){
    var type: Int? = null
    var question: String? = null
    var options: RealmList<RealmString>? = null
}