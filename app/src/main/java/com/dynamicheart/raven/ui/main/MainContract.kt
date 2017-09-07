package com.dynamicheart.raven.ui.main

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

/**
 * Created by dynamicheart on 20/8/2017.
 */
object MainContract {

    interface View: MvpView {
    }

    abstract class Presenter: BasePresenter<View>() {
    }
}