package com.dynamicheart.raven.ui.main.inbox

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object InboxContract {
    interface View: MvpView {

    }

    abstract class Presenter: BasePresenter<View>(){

    }
}