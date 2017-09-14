package com.dynamicheart.raven.ui.findhouse

import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.afollestad.materialdialogs.MaterialDialog
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class FindHouseActivity: BaseActivity(), FindHouseContract.View {

    @Inject lateinit var presenter: FindHousePresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.search_view)lateinit var searchView: FloatingSearchView
    @BindView(R.id.card_view) lateinit var cardView: CardView
    @BindView(R.id.text_house_name) lateinit var textHouseName: TextView
    @BindView(R.id.text_house_description) lateinit var textHouseDescription: TextView
    @BindView(R.id.text_house_created_time) lateinit var textHouseCreatedTime: TextView
    @BindView(R.id.text_house_founder_name) lateinit var textHouseFounderName: TextView
    @BindView(R.id.text_house_total_number) lateinit var textHouseTotalNumber: TextView
    @BindView(R.id.text_house_status) lateinit var textHouseStatus: TextView
    @BindView(R.id.text_house_publicity) lateinit var textHousePublicity: TextView
    @BindView(R.id.button_serve) lateinit var buttonServe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_search_house)
        ButterKnife.bind(this)

        searchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener{
            override fun onSearchAction(currentQuery: String) {
                presenter.searchHouse(currentQuery)
            }

            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion) {
            }
        })

        searchView.setOnQueryChangeListener { _, _ -> cardView.visibility = View.INVISIBLE }

        buttonServe.setOnClickListener {
            presenter.serveHouse()
        }

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showCreateServeDialog() {
        MaterialDialog.Builder(this)
                .title(R.string.create_serve_title)
                .content(R.string.create_serve_description)
                .input(R.string.create_serve_hint, R.string.create_serve_pre_fill) { _, input ->
                    presenter.applyToServeHouse(input.toString())
                }.show()
    }

    override fun showResult(house: House) {
        textHouseName.text = house.name
        textHouseDescription.text = house.description
        textHouseCreatedTime.text = house.createdDate.toString()
        textHouseFounderName.text = house.founder?.username
        textHousePublicity.text = if(house.publicity == true) "公开" else "不公开"
        textHouseTotalNumber.text = house.memberNumbers.toString()
        textHouseStatus.text = house.status.toString()
        cardView.visibility = View.VISIBLE
    }

    override fun showResultEmpty() {
        toastHelper.showShortToast(R.string.search_empty)
    }

    override fun showApplyServeSuccess() {
        toastHelper.showShortToast(R.string.apply_server_success)
    }
}