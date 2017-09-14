package com.dynamicheart.raven.ui.raven

import android.os.Bundle
import butterknife.ButterKnife
import com.dynamicheart.raven.ui.base.BaseActivity
import javax.inject.Inject

class RavenActivity: BaseActivity(), RavenContract.View {

    @Inject lateinit var presenter: RavenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        ButterKnife.bind(this)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}