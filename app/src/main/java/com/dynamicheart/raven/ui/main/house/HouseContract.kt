package com.dynamicheart.raven.ui.main.house

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object HouseContract {
    interface View: MvpView{

    }

    abstract class Presenter: BasePresenter<View>(){

    }
}