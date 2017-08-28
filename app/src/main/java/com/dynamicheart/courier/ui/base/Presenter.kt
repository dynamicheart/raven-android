package com.dynamicheart.courier.ui.base

/**
 * Created by dynamicheart on 20/8/2017.
 */
interface Presenter<in V : MvpView> {
    fun attachView(view: V)
    fun detachView()
}