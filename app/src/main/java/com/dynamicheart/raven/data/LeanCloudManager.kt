package com.dynamicheart.raven.data

import android.content.Context
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVInstallation
import com.avos.avoscloud.SaveCallback
import com.avos.avoscloud.im.v2.AVIMClient
import com.dynamicheart.raven.data.model.leancloud.Installation
import com.dynamicheart.raven.data.model.leancloud.form.UpdateInstallationForm
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.injection.ApplicationContext
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton




@Singleton
class LeanCloudManager
@Inject constructor(@ApplicationContext context: Context,
                    private val ravenService: RavenService,
                    private val toastHelper: ToastHelper) {

    companion object {
        @JvmStatic val LEAN_CLOUD_APP_ID = "TFJOwzoaKwEFSMyIMIV4XPHY-gzGzoHsz"
        @JvmStatic val LEAN_CLOUD_APP_KEY = "InFOgxkqvOfz5Bl9YaXijdHd"

        @JvmStatic var leanCloudServiceEnabled = false
    }

    lateinit var avImClient: AVIMClient

    fun registerInstallation() {
        Observable.create<Installation>({ emitter ->
            AVInstallation.getCurrentInstallation().saveInBackground(object : SaveCallback() {
                override fun done(e: AVException?) {
                    if (e == null) {
                        emitter.onNext(Installation(AccountManager.user?.id, AVInstallation.getCurrentInstallation().installationId))
                        emitter.onComplete()
                    } else {
                        emitter.onError(e)
                    }
                }
            })
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap({ uploadInstallation(it) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            leanCloudServiceEnabled = true
                        },
                        onError = { toastHelper.showShortToast(com.dynamicheart.raven.R.string.message_installation_fail) }
                )
    }

    fun initializeImService() {
        avImClient = AVIMClient.getInstance(AccountManager.user?.id)
    }

    private fun uploadInstallation(installation: Installation): Observable<Installation> {
        return ravenService.uploadInstallation(UpdateInstallationForm(installation.installationId))
    }
}
