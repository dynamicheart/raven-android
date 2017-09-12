package com.dynamicheart.raven.ui.main.outbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.base.BaseFragment
import javax.inject.Inject

class OutboxFragment : BaseFragment(),OutboxContract.View {

    @Inject lateinit var presenter: OutboxPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_outbox, container, false)
        ButterKnife.bind(this, view)

        presenter.attachView(this)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}