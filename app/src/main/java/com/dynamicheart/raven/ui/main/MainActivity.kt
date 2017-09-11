package com.dynamicheart.raven.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.draft.DraftActivity
import com.dynamicheart.raven.ui.main.conferbox.ConferBoxFragment
import com.dynamicheart.raven.ui.main.house.HouseFragment
import com.dynamicheart.raven.ui.main.inbox.InboxFragment
import com.dynamicheart.raven.ui.main.outbox.OutboxFragment
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View,NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var accountManager: AccountManager

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.drawer) lateinit var drawer: DrawerLayout
    @BindView(R.id.nav) lateinit var nav: NavigationView
    @BindView(R.id.tab) lateinit var tab: TabLayout
    @BindView(R.id.viewpager) lateinit var viewPager: ViewPager
    @BindView(R.id.fab_draft) lateinit var buttonDraft: FloatingActionButton

    lateinit var imageNavAvatar: CircleImageView
    lateinit var textNavUsername: TextView
    lateinit var textNavEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        if(!accountManager.ensureActiveTokenAvailability(this)){
            return
        }

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        nav.setNavigationItemSelectedListener(this)
        val navHeader = nav.getHeaderView(0)
        imageNavAvatar = navHeader.findViewById(R.id.image_nav_avatar)
        textNavUsername = navHeader.findViewById(R.id.text_nav_username)
        textNavEmail = navHeader.findViewById(R.id.text_nav_email)


        buttonDraft.setOnClickListener {
            startActivity(Intent(this, DraftActivity::class.java))
        }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragments(arrayListOf(InboxFragment(),OutboxFragment(), HouseFragment(), ConferBoxFragment()))

        presenter.attachView(this)
        presenter.registerInstallation()
        presenter.loadUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populateNavHeader(user: User?) {
        textNavUsername.text = user?.username
        textNavEmail.text = user?.email
    }

    inner class ViewPagerAdapter constructor(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragments(fragments: ArrayList<Fragment>) {
            mFragmentList.addAll(fragments)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null
        }
    }
}