package com.dynamicheart.raven.ui.memberlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.member.MemberActivity
import com.dynamicheart.raven.util.andorid.RecyclerItemClickListener
import javax.inject.Inject

class MemberListActivity: BaseActivity(), MemberListContract.View {

    companion object {
        @JvmStatic val IN_MEMORY_DB_CURRENT_MEMBER = "IN_MEMORY_DB_CURRENT_MEMBER"
    }

    @Inject lateinit var presenter: MemberListPresenter
    @Inject lateinit var membersAdapter: MembersAdapter

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_member_list)
        ButterKnife.bind(this)

        recyclerView.adapter = membersAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(this, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@MemberListActivity, MemberActivity::class.java)
                intent.putExtra(MemberActivity.EXTRA_CURRENT_MEMBER_INDEX, position)
                startActivity(intent)
            }

            override fun onLongItemClick(view: View?, position: Int) {

            }
        }))

        presenter.attachView(this)
        presenter.loadMembers()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showMembers(members: List<Member>) {
        membersAdapter.members = members
        membersAdapter.notifyDataSetChanged()
    }
}