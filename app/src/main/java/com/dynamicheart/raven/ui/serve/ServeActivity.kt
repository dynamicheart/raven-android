package com.dynamicheart.raven.ui.serve

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.serve.Serve
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class ServeActivity: BaseActivity(), ServeContract.View {

    @Inject lateinit var presenter: ServePresenter
    @Inject lateinit var servesAdapter: ServesAdapter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_serve)
        ButterKnife.bind(this)

        recyclerView.adapter = servesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        presenter.attachView(this)
        presenter.fetchServes()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showServes(serves: List<Serve>) {
        servesAdapter.serves = ArrayList(serves)
        servesAdapter.notifyDataSetChanged()
    }

    override fun showServesEmpty() {
        servesAdapter.serves = ArrayList()
        toastHelper.showShortToast(R.string.serve_empty)
        servesAdapter.notifyDataSetChanged()
    }

    override fun showToast(resId: Int) {
        toastHelper.showShortToast(resId)
    }
}