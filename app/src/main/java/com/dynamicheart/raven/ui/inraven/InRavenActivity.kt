package com.dynamicheart.raven.ui.inraven

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.afollestad.materialdialogs.MaterialDialog
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.main.inbox.InboxFragment
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class InRavenActivity : BaseActivity(), InRavenContract.View {

    companion object {
        @JvmStatic val EXTRA_RAVEN_ID = "EXTRA_RAVEN_ID"
        @JvmStatic val EXTRA_FROM_IN_RAVEN_FRAGMENT = "EXTRA_FROM_IN_RAVEN_FRAGMENT"
    }

    @Inject lateinit var presenter: InRavenPresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.text_in_raven_detail_title) lateinit var textInRavenTile: TextView
    @BindView(R.id.text_in_raven_detail_description) lateinit var textInRavenDescription: TextView
    @BindView(R.id.text_in_raven_detail_addresser) lateinit var textInRavenAddresser: TextView
    @BindView(R.id.button_in_raven_receive) lateinit var buttonInRavenReceive: Button
    @BindView(R.id.button_whistle_blowing) lateinit var buttonWhistleBlowing: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_in_raven)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        buttonInRavenReceive.setOnClickListener {
            presenter.sendReply()
        }

        buttonWhistleBlowing.setOnClickListener {
            MaterialDialog.Builder(this)
                    .title(R.string.whistle_blowing)
                    .content(R.string.whistle_blowing_reason)
                    .input(R.string.whistle_blowing_hint, R.string.whistle_blowing_hint) { _, input ->
                        presenter.sendWhistleBlowing(input.toString())
                    }.show()
        }

        presenter.attachView(this)
        if(intent.getBooleanExtra(EXTRA_FROM_IN_RAVEN_FRAGMENT, false)){
            presenter.loadInRavenFromLocal(intent.getIntExtra(InboxFragment.EXTRA_IN_RAVEN_INDEX, 0))
        }else{
            presenter.loadInRavenFromRemote(intent.getStringExtra(EXTRA_RAVEN_ID))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showInRaven(inRaven: InRaven) {
        textInRavenTile.text = inRaven.title
        textInRavenDescription.text = inRaven.description
        textInRavenAddresser.text = inRaven.addresser?.username
    }

    override fun showToast(resId: Int) {
        toastHelper.showShortToast(resId)
    }
}