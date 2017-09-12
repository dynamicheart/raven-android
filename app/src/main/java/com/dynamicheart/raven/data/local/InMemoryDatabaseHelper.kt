package com.dynamicheart.raven.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryDatabaseHelper
@Inject constructor(){

    companion object {
        @JvmStatic val dbManager = HashMap<String, HashMap<String, Any>>()
    }

    fun store(activityId: String, key: String, value: Any){
        val db = dbManager[activityId]?: HashMap<String, Any>()
        db[key] =  value
        dbManager[activityId] = db
    }

    fun delete(activityId: String, key: String){
        dbManager[activityId]?.remove(key)
    }

    fun delete(activityId: String){
        dbManager.remove(activityId)
    }
}