package com.dynamicheart.raven.ui.updatehouse

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.UpdateHouseForm
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object UpdateHouseContract {
    interface View: MvpView{
        fun showHouse(house: House)
        fun showToast(resId: Int)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadHouse()
        abstract fun updateHouse(updateHouseForm: UpdateHouseForm)
    }
}