package com.dynamicheart.raven.ui.serve

import com.dynamicheart.raven.data.model.serve.Serve
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object ServeContract {
    interface View: MvpView{
        fun showServes(serves: List<Serve>)
        fun showServesEmpty()
        fun showToast(resId: Int)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun fetchServes()
    }
}