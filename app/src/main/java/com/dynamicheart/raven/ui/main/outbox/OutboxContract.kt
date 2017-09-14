package com.dynamicheart.raven.ui.main.outbox

import com.dynamicheart.raven.data.model.raven.Raven
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object OutboxContract {
    interface View : MvpView {
        fun showRavens(ravens: List<Raven>)
        fun showRavensEmpty()
        fun showError()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadRavens()
    }

}