package com.dynamicheart.raven.data.local

import com.dynamicheart.raven.data.model.raven.InRaven
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DatabaseHelper
@Inject constructor(private val realmProvider: Provider<Realm>) {
    fun setInRavens(inRavens: List<InRaven>): Observable<List<InRaven>> {
        return Observable.create<List<InRaven>>({ emitter ->
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
                val inRavens = realm.copyFromRealm(realm.where(InRaven::class.java).findAll().sort("createdDate", Sort.DESCENDING))
                emitter.onNext(inRavens)
                emitter.onComplete()
            } catch (e: Exception) {
                Timber.e(e)
                emitter.onError(e)
            }
        })
    }

    fun getOneInRaven(id: String): Observable<InRaven>{
        return Observable.create<InRaven>({ emitter ->
            try{
                val realm = realmProvider.get()
                val result = realm.where(InRaven::class.java).equalTo("id", id).findFirst()
                if(result != null){
                    emitter.onNext(realm.copyFromRealm(result))
                    emitter.onComplete()
                }else {
                    emitter.onError(Exception("Local find failed"))
                }
            } catch (e: Exception){
                emitter.onError(e)
            }
        })
    }
}