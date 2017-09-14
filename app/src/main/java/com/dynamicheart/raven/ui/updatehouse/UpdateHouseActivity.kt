package com.dynamicheart.raven.ui.updatehouse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.UpdateHouseForm
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class UpdateHouseActivity: BaseActivity(), UpdateHouseContract.View {

    @Inject lateinit var presenter: UpdateHousePresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.edit_text_house_name) lateinit var editTextHouseName: EditText
    @BindView(R.id.edit_text_house_description) lateinit var editTextHouseDescription: EditText
    @BindView(R.id.button_update) lateinit var buttonUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_house_update)
        ButterKnife.bind(this)

        buttonUpdate.setOnClickListener {
            presenter.updateHouse(UpdateHouseForm(
                    editTextHouseName.text.toString(),
                    editTextHouseDescription.text.toString(),
                    null
            ))
        }

        presenter.attachView(this)
        presenter.loadHouse()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showHouse(house: House) {
        this.editTextHouseName.setText(house.name)
        this.editTextHouseDescription.setText(house.description)
    }

    override fun showToast(resId: Int) {
        toastHelper.showShortToast(resId)
    }
}