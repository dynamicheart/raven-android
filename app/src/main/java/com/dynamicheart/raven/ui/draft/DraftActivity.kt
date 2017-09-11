package com.dynamicheart.raven.ui.draft

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.base.BaseActivity
import javax.inject.Inject

class DraftActivity: BaseActivity(), DraftContract.View {

    @Inject lateinit var presenter: DraftPresenter

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_draft)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        presenter.attachView(this)
    }
}