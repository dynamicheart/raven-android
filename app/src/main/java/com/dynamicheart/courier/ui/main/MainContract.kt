package com.dynamicheart.courier.ui.main

import com.dynamicheart.courier.ui.base.BasePresenter
import com.dynamicheart.courier.ui.base.MvpView

/**
 * Created by dynamicheart on 20/8/2017.
 */
object MainContract {

    interface View: MvpView {
    }

    abstract class Presenter: BasePresenter<View>() {
    }
}