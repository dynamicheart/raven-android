package com.dynamicheart.raven.ui.createhouse

import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object CreateHouseContract {
    interface View: MvpView{
        fun goToHouseDetailActivity()
        fun showError(msg: String)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun createHouse(createHouseForm: CreateHouseForm)
    }
}