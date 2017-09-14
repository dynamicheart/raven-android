package com.dynamicheart.raven.ui.housedetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.main.house.HouseFragment
import com.dynamicheart.raven.ui.memberlist.MemberListActivity
import com.dynamicheart.raven.ui.serve.ServeActivity
import com.dynamicheart.raven.ui.updatehouse.UpdateHouseActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class HouseDetailActivity : BaseActivity(), HouseDetailContract.View {

    companion object {
        @JvmStatic
        val START_FROM_HOUSE_CREATE_ACTIVITY = "START_FROM_HOUSE_CREATE_ACTIVITY"
        @JvmStatic
        val IN_MEMORY_DB_CURRENT_HOUSE = "IN_MEMORY_DB_HOUSE_LIST"
    }

    @Inject lateinit var presenter: HouseDetailPresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.text_house_id) lateinit var textHouseId: TextView
    @BindView(R.id.text_house_name) lateinit var textHouseName: TextView
    @BindView(R.id.text_house_description) lateinit var textHouseDescription: TextView
    @BindView(R.id.text_house_created_time) lateinit var textHouseCreatedTime: TextView
    @BindView(R.id.text_house_founder_name) lateinit var textHouseFounderName: TextView
    @BindView(R.id.text_house_total_number) lateinit var textHouseTotalNumber: TextView
    @BindView(R.id.text_house_status) lateinit var textHouseStatus: TextView
    @BindView(R.id.text_house_publicity) lateinit var textHousePublicity: TextView
    @BindView(R.id.button_edit) lateinit var buttonEdit: Button
    @BindView(R.id.button_exit) lateinit var buttonExit: Button
    @BindView(R.id.button_look_up_members) lateinit var buttonLookUpMembers: Button
    @BindView(R.id.button_get_serves) lateinit var buttonGetServes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_house_detail)
        ButterKnife.bind(this)


        buttonEdit.setOnClickListener {
            startActivity(Intent(this, UpdateHouseActivity::class.java))
        }

        buttonExit.setOnClickListener {
            presenter.leaveHouse()
        }

        buttonLookUpMembers.setOnClickListener {
            startActivity(Intent(this, MemberListActivity::class.java))
        }

        buttonGetServes.setOnClickListener{
            startActivity(Intent(this, ServeActivity::class.java))
        }

        presenter.attachView(this)
        if (intent.getBooleanExtra(START_FROM_HOUSE_CREATE_ACTIVITY, false))
            presenter.loadHouse()
        else presenter.loadHouse(intent.getIntExtra(HouseFragment.EXTRA_HOUSE_INDEX, 0))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showHouse(house: House) {
        textHouseId.text = house.id
        textHouseName.text = house.name
        textHouseDescription.text = house.description
        textHouseCreatedTime.text = house.createdDate.toString()
        textHouseFounderName.text = house.founder?.username
        textHousePublicity.text = if (house.publicity == true) "公开" else "不公开"
        textHouseTotalNumber.text = house.memberNumbers.toString()
        textHouseStatus.text = house.status.toString()
        if(AccountManager.user?.id == house.founder?.id){
            buttonEdit.visibility = View.VISIBLE
            buttonGetServes.visibility = View.VISIBLE
        }
    }

    override fun showLeaveHouseSuccess() {
        toastHelper.showShortToast(R.string.house_exit_success)
        finish()
    }
}