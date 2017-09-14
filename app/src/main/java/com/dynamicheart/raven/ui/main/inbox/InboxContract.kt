package com.dynamicheart.raven.ui.main.inbox

import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object InboxContract {
    interface View: MvpView {
        fun showInRavens(inRavens: List<InRaven>)
        fun showInRavensEmpty()
        fun showError()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadInRavens()
    }
}