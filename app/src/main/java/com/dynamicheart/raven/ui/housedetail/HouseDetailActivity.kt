package com.dynamicheart.raven.ui.housedetail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.main.house.HouseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import javax.inject.Inject

class HouseDetailActivity : BaseActivity(), HouseDetailContract.View {

    @Inject lateinit var presenter: HouseDetailPresenter

    @BindView(R.id.text_house_name) lateinit var textHouseName: TextView
    @BindView(R.id.text_house_description) lateinit var textHouseDescription: TextView
    @BindView(R.id.text_house_created_time) lateinit var textHouseCreatedTime: TextView
    @BindView(R.id.text_house_founder_name) lateinit var textHouseFounderName: TextView
    @BindView(R.id.text_house_total_number) lateinit var textHouseTotalNumber: TextView
    @BindView(R.id.text_house_status) lateinit var textHouseStatus: TextView
    @BindView(R.id.text_house_publicity) lateinit var textHousePublicity: TextView
    @BindView(R.id.button_edit) lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_house_detail)
        ButterKnife.bind(this)

        presenter.attachView(this)
        presenter.loadHouse(intent.getIntExtra(HouseFragment.IN_MEMORY_DB_HOUSE_INDEX, 0))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showHouse(house: House) {
        textHouseName.text = house.name
        textHouseDescription.text = house.description
        textHouseCreatedTime.text = house.createdDate.toString()
        textHouseFounderName.text = house.founder?.username
        textHousePublicity.text = if(house.publicity == true) "公开" else "不公开"
        textHouseTotalNumber.text = house.memberNumbers.toString()
        textHouseStatus.text = house.status.toString()
    }
}