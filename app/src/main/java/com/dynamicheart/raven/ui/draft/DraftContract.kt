package com.dynamicheart.raven.ui.draft

import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object DraftContract {
    interface View: MvpView{

    }

    abstract class Presenter: BasePresenter<View>(){

    }
}