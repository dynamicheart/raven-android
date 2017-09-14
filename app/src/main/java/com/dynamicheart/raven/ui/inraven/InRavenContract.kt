package com.dynamicheart.raven.ui.inraven

import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object InRavenContract {
    interface View: MvpView{
        fun showInRaven(inRaven: InRaven)
        fun showToast(resId: Int)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadInRavenFromLocal(index: Int)
        abstract fun loadInRavenFromRemote(id: String)
        abstract fun sendReply()
        abstract fun sendWhistleBlowing(description: String)
    }
}