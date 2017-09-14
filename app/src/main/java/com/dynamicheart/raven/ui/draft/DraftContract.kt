package com.dynamicheart.raven.ui.draft

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object DraftContract {
    interface View: MvpView{
        fun showToast(resId: Int)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun sendRaven(title: String, content: String)
    }
}