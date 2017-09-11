package com.dynamicheart.raven.util.realm

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class RealmString : RealmObject(){
   var value: String? = null
}