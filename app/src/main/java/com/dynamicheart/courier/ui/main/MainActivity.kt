package com.dynamicheart.courier.ui.main

import com.dynamicheart.courier.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by dynamicheart on 20/8/2017.
 */
class MainActivity : BaseActivity(), MainContract.View {

    @Inject lateinit var presenter: MainPresenter

}