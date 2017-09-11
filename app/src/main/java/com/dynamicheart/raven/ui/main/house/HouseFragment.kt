package com.dynamicheart.raven.ui.main.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.base.BaseFragment
import javax.inject.Inject

class HouseFragment : BaseFragment(), HouseContract.View {

    @Inject lateinit var presenter: HousePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_house, container, false)
        ButterKnife.bind(this, view)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}