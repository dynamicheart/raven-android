package com.dynamicheart.raven.ui.housedetail

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object HouseDetailContract {
    interface View: MvpView{
        fun  showHouse(house: House)
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadHouse(index: Int)
    }
}