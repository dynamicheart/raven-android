package com.dynamicheart.raven.ui.select

import android.os.Bundle
import android.view.MenuItem
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.select.selecthouse.SelectHouseFragment
import com.dynamicheart.raven.ui.select.selectmember.SelectMemberFragment
import timber.log.Timber
import javax.inject.Inject

class SelectActivity: BaseActivity(), SelectContract.View {

    @Inject lateinit var presenter: SelectPresenter

    lateinit var menuItemConfirm: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_select)
        ButterKnife.bind(this)

        presenter.attachView(this)
        showSelectHouseFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showSelectHouseFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SelectHouseFragment())
                .addToBackStack(SelectHouseFragment::class.java.name)
                .commit()
    }

    override fun showSelectMemberFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SelectMemberFragment())
                .addToBackStack(SelectMemberFragment::class.java.name)
                .commit()
    }

    override fun onBackPressed() {
        Timber.i("%d fragments left currently", supportFragmentManager.backStackEntryCount)
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun goBackToPreviousActivity() {
        finish()
    }
}