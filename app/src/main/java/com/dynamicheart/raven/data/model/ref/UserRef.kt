package com.dynamicheart.raven.data.model.ref

import io.realm.RealmObject

open class UserRef: RealmObject(){
    var id: String? = null
    var username: String? = null
}