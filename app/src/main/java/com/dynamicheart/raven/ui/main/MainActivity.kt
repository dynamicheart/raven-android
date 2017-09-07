package com.dynamicheart.raven.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by dynamicheart on 20/8/2017.
 */
class MainActivity : BaseActivity(), MainContract.View {

    @Inject lateinit var presenter: MainPresenter

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
    }
}