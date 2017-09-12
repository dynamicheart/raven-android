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
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.LeanCloudManager
import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.createhouse.CreateHouseActivity
import com.dynamicheart.raven.ui.draft.DraftActivity
import com.dynamicheart.raven.ui.housedetail.HouseDetailActivity
import com.dynamicheart.raven.ui.main.conferbox.ConferBoxFragment
import com.dynamicheart.raven.ui.main.house.HouseFragment
import com.dynamicheart.raven.ui.main.inbox.InboxFragment
import com.dynamicheart.raven.ui.main.outbox.OutboxFragment
import com.dynamicheart.raven.ui.user.UserActivity
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View,NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var accountManager: AccountManager
    @Inject lateinit var leanCloudManager: LeanCloudManager

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.drawer) lateinit var drawer: DrawerLayout
    @BindView(R.id.nav) lateinit var nav: NavigationView
    @BindView(R.id.tab) lateinit var tab: TabLayout
    @BindView(R.id.viewpager) lateinit var viewPager: ViewPager
    @BindView(R.id.fab_draft) lateinit var buttonDraft: FloatingActionButton

    private lateinit var imageNavAvatar: CircleImageView
    private lateinit var textNavUsername: TextView
    private lateinit var textNavEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        if(!accountManager.ensureActiveTokenAvailability(this)){
            return
        }else {
            leanCloudManager.registerInstallation()
            leanCloudManager.initializeImService()
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
        viewPager.adapter = adapter
        tab.setupWithViewPager(viewPager)
        tab.getTabAt(0)?.setIcon(R.drawable.ic_inbox_white_24dp)
        tab.getTabAt(1)?.setIcon(R.drawable.ic_send_white_24dp)
        tab.getTabAt(2)?.setIcon(R.drawable.ic_group_white_24dp)
        tab.getTabAt(3)?.setIcon(R.drawable.ic_chat_white_24dp)

        presenter.attachView(this)
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
        when(item.itemId){
            R.id.menu_house_create -> {
                startActivity(Intent(this, CreateHouseActivity::class.java))
                return true
            }
            R.id.menu_house_join -> {

                return true
            }
            R.id.menu_user_profile -> {
                startActivity(Intent(this, UserActivity::class.java))
                return true
            }
        }
        return false
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