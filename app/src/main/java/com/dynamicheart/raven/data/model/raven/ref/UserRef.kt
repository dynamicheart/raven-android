package com.dynamicheart.raven.data.model.raven.ref

import io.realm.RealmObject

/**
 * Created by jianbangyang on 2017/9/5.
 *
 */
open class UserRef: RealmObject(){
    var id: String? = null
    var username: String? = null
}