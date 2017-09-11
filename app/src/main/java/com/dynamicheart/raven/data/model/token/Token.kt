package com.dynamicheart.raven.data.model.token

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Token: RealmObject() {
    @PrimaryKey var id: String? = null
    var authentication: String? = null
}