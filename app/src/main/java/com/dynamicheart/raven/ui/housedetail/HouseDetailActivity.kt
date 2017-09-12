package com.dynamicheart.raven.ui.housedetail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HouseDetailActivity : BaseActivity() {

    companion object {

    }

    @Inject lateinit var ravenService: RavenService

    @BindView(R.id.edit_text_house_name) lateinit var editTextHouseName: EditText
    @BindView(R.id.edit_text_house_description) lateinit var editTextHouseDescription: EditText
    @BindView(R.id.button_create) lateinit var buttonCreate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_house_create)
        ButterKnife.bind(this)
        buttonCreate.setOnClickListener {
            ravenService.createNewHouse(
                    CreateHouseForm(
                            editTextHouseName.text.toString(),
                            editTextHouseDescription.text.toString(),
                            null
                    )
            ).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onNext = {
                                this.finish()
                            },
                            onError = {}
                    )
        }
    }
}