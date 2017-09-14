package com.dynamicheart.raven.ui.findhouse

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object FindHouseContract {

    interface View: MvpView{
        fun showResult(house: House)
        fun showResultEmpty()
        fun showCreateServeDialog()
        fun showApplyServeSuccess()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun searchHouse(id: String)
        abstract fun serveHouse()
        abstract fun applyToServeHouse(content: String)
    }
}