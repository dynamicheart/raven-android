package com.dynamicheart.raven.ui.main.outbox

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object OutboxContract {
    interface View : MvpView {

    }

    abstract class Presenter : BasePresenter<View>() {

    }

}