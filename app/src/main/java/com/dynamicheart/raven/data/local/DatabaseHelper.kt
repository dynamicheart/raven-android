package com.dynamicheart.raven.data.local

import com.dynamicheart.raven.data.model.raven.InRaven
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by jianbangyang on 2017/9/4.
 *
 */
@Singleton
class DatabaseHelper
@Inject constructor(private val realmProvider: Provider<Realm>) {
    fun setInRavens(inRavens: Collection<InRaven>): Observable<Collection<InRaven>> {
        return Observable.create<Collection<InRaven>>({ emitter ->
            try {
                val realm = realmProvider.get()
                realm.executeTransaction { realmParam ->
                    realmParam.copyToRealmOrUpdate(inRavens)
                }
                emitter.onNext(inRavens)
                emitter.onComplete()
            } catch (e: Exception) {
                Timber.e(e)
                emitter.onError(e)
            }
        })
    }

    fun getInRavens(): Observable<List<InRaven>> {
        return Observable.create<List<InRaven>>({ emitter ->
            try {
                val realm = realmProvider.get()
                val inRavens = realm.copyFromRealm(realm.where(InRaven::class.java).findAllAsync())
                emitter.onNext(inRavens)
                emitter.onComplete()
            } catch (e: Exception) {
                Timber.e(e)
                emitter.onError(e)
            }
        })
    }
}