package com.dynamicheart.raven.ui.main.house

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BasePresenter
import com.dynamicheart.raven.ui.base.MvpView

object HouseContract {
    interface View: MvpView{
        fun showHouses(houses: List<House>)
        fun showHousesEmpty()
        fun showError()
    }

    abstract class Presenter: BasePresenter<View>(){
        abstract fun fetchHouses()
    }
}