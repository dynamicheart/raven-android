package com.dynamicheart.raven.data.remote

import android.app.Activity
import android.content.Context
import com.avos.avoscloud.AVInstallation
import com.avos.avoscloud.AVOSCloud
import com.avos.avoscloud.SaveCallback
import com.dynamicheart.raven.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import com.avos.avoscloud.AVException
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.model.leancloud.Installation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Singleton
class LeanCloudService
@Inject constructor(@ApplicationContext context: Context, private val ravenService: RavenService){

    companion object {
        @JvmStatic private val LEAN_CLOUD_APP_ID = "TFJOwzoaKwEFSMyIMIV4XPHY-gzGzoHsz"
        @JvmStatic private val LEAN_CLOUD_APP_KEY = "InFOgxkqvOfz5Bl9YaXijdHd"

        @JvmStatic private var leanCloudServiceEnabled = false
    }

    init {
        AVOSCloud.initialize(context, LEAN_CLOUD_APP_ID, LEAN_CLOUD_APP_KEY)
        AVOSCloud.setDebugLogEnabled(true)
    }

    fun registerInstallation(): Observable<Installation>{
        return Observable.create<Installation>( { emitter ->
            AVInstallation.getCurrentInstallation().saveInBackground(object :SaveCallback(){
                override fun done(e: AVException?) {
                    if(e == null){
                        emitter.onNext(Installation(AccountManager.user?.id, AVInstallation.getCurrentInstallation().installationId))
                        emitter.onComplete()
                    }else{
                        emitter.onError(e)
                    }
                }
            })
        })
    }

    fun uploadInstallation(installation: Installation): Observable<Installation>{
        return ravenService.uploadInstallation(installation.installationId)
    }

    fun isLeanCloudServiceEnabled(): Boolean{
        return leanCloudServiceEnabled
    }

    fun enableLeanCloudService(){
        leanCloudServiceEnabled = true
    }
}
