package com.dynamicheart.raven.data.model.raven

import com.dynamicheart.raven.data.model.raven.optionpoll.OptionPoll
import com.dynamicheart.raven.data.model.ref.HouseRef
import com.dynamicheart.raven.data.model.ref.UserRef
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by jianbangyang on 2017/9/4.
 *
 */
open class InRaven: RealmObject(){
    @PrimaryKey var id: String? = null
    var house: HouseRef? = null
    var addresser: UserRef? = null
    var title: String? = null
    var description: String? = null
    var type: Int? = null
    var optionPolls: RealmList<OptionPoll>? = null
    var attachmentId: String? = null
    var mold: Boolean? = false
    var moldContent: String? = null
    var createdDate: Date? = null
}