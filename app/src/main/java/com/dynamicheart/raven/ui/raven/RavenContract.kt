package com.dynamicheart.raven.ui.raven

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object RavenContract {
    interface View: MvpView

    abstract class Presenter: BasePresenter<View>(){

    }
}