package com.dynamicheart.raven.ui.main.house

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
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BaseFragment
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import com.dynamicheart.raven.util.andorid.RecyclerItemClickListener
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class HouseFragment : BaseFragment(), HouseContract.View {

    @Inject lateinit var presenter: HousePresenter
    @Inject lateinit var toastHelper: ToastHelper
    @Inject lateinit var housesAdapter: HousesAdapter

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_house, container, false)
        ButterKnife.bind(this, view)

        recyclerView.adapter = housesAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(activity, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val house = housesAdapter.houses[position]
                val intent = Intent(activity, HouseDetailActivity::class.java)
            }

            override fun onLongItemClick(view: View?, position: Int) {
            }
        }))

        presenter.attachView(this)
        presenter.fetchHouses()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showHouses(houses: List<House>) {
        housesAdapter.houses = houses
        housesAdapter.notifyDataSetChanged()
    }

    override fun showHousesEmpty() {
        housesAdapter.houses = emptyList()
        housesAdapter.notifyDataSetChanged()
        toastHelper.showShortToast(R.string.empty_houses)
    }

    override fun showError() {
        toastHelper.showShortToast(R.string.error_loading_houses)
    }
}