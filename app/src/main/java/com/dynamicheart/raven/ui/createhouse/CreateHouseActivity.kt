package com.dynamicheart.raven.ui.createhouse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class CreateHouseActivity : BaseActivity(), CreateHouseContract.View{

    @Inject lateinit var presenter: CreateHousePresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.edit_text_house_name) lateinit var editTextHouseName: EditText
    @BindView(R.id.edit_text_house_description) lateinit var editTextHouseDescription: EditText
    @BindView(R.id.button_create) lateinit var buttonCreate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_house_create)
        ButterKnife.bind(this)

        buttonCreate.setOnClickListener {
            presenter.createHouse(CreateHouseForm(
                    editTextHouseName.text.toString(),
                    editTextHouseDescription.text.toString(),
                    null
            ))
        }
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun goToHouseDetailActivity() {
        val intent = Intent(this, HouseDetailActivity::class.java)
        intent.putExtra(HouseDetailActivity.START_FROM_HOUSE_CREATE_ACTIVITY, true)
        startActivity(intent)
        finish()
    }

    override fun showError(msg: String) {
        toastHelper.showShortToast(msg)
    }
}