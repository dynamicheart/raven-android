package com.dynamicheart.raven.ui.draft

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.select.SelectActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class DraftActivity: BaseActivity(), DraftContract.View {

    companion object {
        @JvmStatic val IN_MEMORY_DB_RULING_HOUSE_LIST = "IN_MEMORY_DB_RULING_HOUSE_LIST"
        @JvmStatic val IN_MEMORY_DB_SELECTED_HOUSE = "IN_MEMORY_DB_SELECTED_HOUSE"
        @JvmStatic val IN_MEMORY_DB_SELECTED_MEMBERS = "IN_MEMORY_DB_SELECTED_MEMBERS"
    }


    @Inject lateinit var presenter: DraftPresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.edit_text_title) lateinit var editTextTitle: EditText
    @BindView(R.id.edit_text_content) lateinit var editTextContent: EditText
    @BindView(R.id.button_send) lateinit var buttonSend: Button
    @BindView(R.id.image_menu_item_select) lateinit var imageMenuItemSelect: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_draft)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        buttonSend.setOnClickListener {
            presenter.sendRaven(editTextTitle.text.toString(), editTextContent.text.toString())
        }

        imageMenuItemSelect.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showToast(resId: Int) {
        toastHelper.showShortToast(resId)
    }
}