package com.dynamicheart.raven.ui.main.outbox

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.raven.Raven
import com.dynamicheart.raven.ui.base.BaseFragment
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class OutboxFragment : BaseFragment(),OutboxContract.View {

    @Inject lateinit var presenter: OutboxPresenter
    @Inject lateinit var toastHelper: ToastHelper
    @Inject lateinit var ravensAdapter: RavensAdapter


    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_outbox, container, false)
        ButterKnife.bind(this, view)

        recyclerView.adapter = ravensAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        presenter.attachView(this)
        presenter.loadRavens()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showRavens(ravens: List<Raven>) {
        ravensAdapter.ravens = ravens
        ravensAdapter.notifyDataSetChanged()
    }

    override fun showRavensEmpty() {
        ravensAdapter.ravens = emptyList()
        ravensAdapter.notifyDataSetChanged()
        toastHelper.showShortToast(R.string.empty_ravens)
    }

    override fun showError() {
        toastHelper.showShortToast(R.string.error_loading_ravens)
    }
}