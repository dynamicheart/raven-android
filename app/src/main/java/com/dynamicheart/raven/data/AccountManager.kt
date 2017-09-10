package com.dynamicheart.raven.data

import android.app.Activity
import android.content.Intent
import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AccountManager
@Inject constructor(private val realmProvider: Provider<Realm>){

    private var account: User? = null

    init {
        val realm = realmProvider.get()
        account = realm.copyFromRealm(realm.where(User::class.java).findFirst())
    }

    fun ensureActiveAccountAvailability(activity: Activity): Boolean{
        var accountAvailable = true
        if(!hasAccount()){
            accountAvailable = false
            addAccount(activity, activity.intent)
        }

        if(!accountAvailable){
            activity.finish()
        }
        return accountAvailable
    }

    private fun hasAccount(): Boolean{
        return account != null
    }

    private fun addAccount(activity: Activity, onAddedIntent: Intent){
        activity.startActivity(AuthenticatorActivity.makeIntent(onAddedIntent, activity))
    }
}