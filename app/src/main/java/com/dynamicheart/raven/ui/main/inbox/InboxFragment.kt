package com.dynamicheart.raven.ui.main.inbox

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.ui.base.BaseFragment
import com.dynamicheart.raven.ui.inraven.InRavenActivity
import com.dynamicheart.raven.util.ToastHelper
import com.dynamicheart.raven.util.andorid.RecyclerItemClickListener
import javax.inject.Inject

class InboxFragment : BaseFragment(), InboxContract.View {

    companion object {
        @JvmStatic val IN_MEMORY_DB_IN_RAVEN_LIST = "IN_MEMORY_DB_IN_RAVEN_LIST"
        @JvmStatic val EXTRA_IN_RAVEN_INDEX = "EXTRA_IN_RAVEN_INDEX"
    }

    @Inject lateinit var presenter: InboxPresenter
    @Inject lateinit var toastHelper: ToastHelper
    @Inject lateinit var inRavensAdapter: InRavensAdapter

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_inbox, container, false)
        ButterKnife.bind(this, view)

        recyclerView.adapter = inRavensAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(activity, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val raven = inRavensAdapter.inRavens[position]
                val intent = Intent(activity, InRavenActivity::class.java)
                intent.putExtra(EXTRA_IN_RAVEN_INDEX, position)
                intent.putExtra(InRavenActivity.EXTRA_FROM_IN_RAVEN_FRAGMENT, true)
                startActivity(intent)
            }

            override fun onLongItemClick(view: View?, position: Int) {
            }
        }))

        presenter.attachView(this)
        presenter.loadInRavens()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showInRavens(inRavens: List<InRaven>) {
        inRavensAdapter.inRavens = inRavens
        inRavensAdapter.notifyDataSetChanged()
    }

    override fun showInRavensEmpty() {
        inRavensAdapter.inRavens = emptyList()
        inRavensAdapter.notifyDataSetChanged()
        toastHelper.showShortToast(R.string.empty_in_ravens)
    }

    override fun showError() {
        toastHelper.showShortToast(R.string.error_loading_ravens)
    }
}