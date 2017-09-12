package com.dynamicheart.raven.data

import android.app.Activity
import android.content.Intent
import com.dynamicheart.raven.data.model.token.Token
import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.data.model.user.form.CreateUserForm
import com.dynamicheart.raven.data.model.user.form.LoginForm
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import io.reactivex.Observable
import io.realm.Realm
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AccountManager
@Inject constructor(private val realmProvider: Provider<Realm>,
                    private val ravenService: RavenService) {

    companion object {
        @JvmStatic
        var user: User? = null
        @JvmStatic
        var token: Token? = null
    }

    init {
        Timber.i("Account manager init")
        val realm = realmProvider.get()

        if (user == null) {
            val result = realm.where(User::class.java).findFirst()
            if (result != null) {
                user = realm.copyFromRealm(result)
            }
        }

        if (token == null) {
            val result = realm.where(Token::class.java).findFirst()
            if (result != null) {
                token = realm.copyFromRealm(result)
            }
        }
    }

    fun ensureActiveTokenAvailability(activity: Activity): Boolean {
        var tokenAvailable = true
        if (!hasToken()) {
            tokenAvailable = false
            getToken(activity, activity.intent)
        }

        if (!tokenAvailable) {
            activity.finish()
        }
        return tokenAvailable
    }

    private fun hasToken(): Boolean {
        return token != null
    }

    private fun getToken(activity: Activity, onAddedIntent: Intent) {
        activity.startActivity(AuthenticatorActivity.makeIntent(onAddedIntent, activity))
    }

    fun fetchToken(loginForm: LoginForm): Observable<Token> {
        return ravenService.login(loginForm)
                .flatMap { saveToken(it) }
    }

    fun createUser(createUserForm: CreateUserForm): Observable<User> {
        return ravenService.createNewUser(createUserForm)
                .flatMap { saveUser(it) }
    }

    fun syncUser(): Observable<User> {
        return ravenService.getUser()
                .flatMap { saveUser(it) }
    }

    fun checkout(): Observable<Void> {
        return Observable.create<Void>({ emitter ->
            try {
                val realm = realmProvider.get()
                realm.beginTransaction()
                realm.where(User::class.java).findAll().deleteAllFromRealm()
                realm.where(Token::class.java).findAll().deleteAllFromRealm()
                realm.commitTransaction()
                AccountManager.user = null
                AccountManager.token = null
                emitter.onComplete()
            } catch (e : Exception) {
                Timber.e(e)
                emitter.onError(e)
            }
        })
    }

    private fun saveToken(newToken: Token): Observable<Token> {
        return Observable.create<Token>({ emitter ->
            try {
                val realm = realmProvider.get()
                val result = realm.where(Token::class.java).findFirst()
                if (result != null) {
                    val dbToken = realm.copyFromRealm(result)
                    if (dbToken.id != newToken.id) {
                        realm.executeTransaction { realmParam ->
                            realmParam.deleteAll()
                        }
                    }
                }
                realm.executeTransaction { realmParam ->
                    realmParam.copyToRealmOrUpdate(newToken)
                }
                token = newToken
                emitter.onNext(newToken)
                emitter.onComplete()
            } catch (e: Exception) {
                Timber.e(e)
                emitter.onError(e)
            }
        })
    }

    private fun saveUser(newUser: User): Observable<User> {
        return Observable.create<User>({ emitter ->
            try {
                val realm = realmProvider.get()
                realm.executeTransaction { realmParam ->
                    realmParam.copyToRealmOrUpdate(newUser)
                }
                user = newUser
                emitter.onNext(newUser)
                emitter.onComplete()
            } catch (e: Exception) {
                Timber.e(e)
                emitter.onError(e)
            }
        })
    }
}