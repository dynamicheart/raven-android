package com.dynamicheart.raven.ui.select.selecthouse

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object SelectHouseContract {
    interface View: MvpView{
        fun showHouseList(houses: List<House>)
        fun showHousesEmpty()
        fun showError()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun loadHouses()
        abstract fun recordSelectedHouse(index: Int)
    }
}