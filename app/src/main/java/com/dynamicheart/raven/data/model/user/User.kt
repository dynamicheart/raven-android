package com.dynamicheart.raven.data.model.user

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class User: RealmObject(){
    @PrimaryKey var id: String? = null
    var username: String? = null
    var email: String? = null
    var phoneNumber: String? = null
    var avatar: String? = null
    var status: Int? = null
    var createdDate: Date? = null
}