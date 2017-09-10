package com.dynamicheart.raven.data.model.user

import io.realm.RealmObject

/**
 * Created by dynamicheart on 20/8/2017.
 */
open class User: RealmObject(){
    val id: Long? = null
    var username: String? = null
    var email: String? = null
    var phoneNumber: String? = null
    val avatar: String? = null
}