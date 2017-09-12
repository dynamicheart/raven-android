package com.dynamicheart.raven.ui.housedetail

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object HouseDetailContract {
    interface View: MvpView

    abstract class Presenter: BasePresenter<View>(){

    }
}