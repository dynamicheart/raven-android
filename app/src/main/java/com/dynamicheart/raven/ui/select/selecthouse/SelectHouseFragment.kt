package com.dynamicheart.raven.ui.select.selecthouse

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BaseFragment
import com.dynamicheart.raven.ui.select.SelectActivity
import com.dynamicheart.raven.util.ToastHelper
import com.dynamicheart.raven.util.andorid.RecyclerItemClickListener
import javax.inject.Inject

class SelectHouseFragment: BaseFragment(), SelectHouseContract.View {

    companion object {
        @JvmStatic val IN_MEMORY_DB_SELECTED_HOUSE_INDEX = "IN_MEMORY_DB_SELECTED_HOUSE_INDEX"
    }

    @Inject lateinit var presenter: SelectHousePresenter
    @Inject lateinit var toastHelper: ToastHelper
    @Inject lateinit var selectableHousesAdapter: SelectableHousesAdapter

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_house, container, false)
        ButterKnife.bind(this, view)

        recyclerView.adapter = selectableHousesAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(activity, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                presenter.recordSelectedHouse(position)
                (activity as SelectActivity).showSelectMemberFragment()
            }

            override fun onLongItemClick(view: View?, position: Int) {

            }
        }))

        presenter.attachView(this)
        presenter.loadHouses()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showHouseList(houses: List<House>) {
        selectableHousesAdapter.houses = houses
        selectableHousesAdapter.notifyDataSetChanged()
    }

    override fun showHousesEmpty() {
        selectableHousesAdapter.houses = emptyList()
        selectableHousesAdapter.notifyDataSetChanged()
        toastHelper.showShortToast(R.string.select_empty_houses)
    }

    override fun showError() {
        toastHelper.showShortToast(R.string.error_loading_houses)
    }
}