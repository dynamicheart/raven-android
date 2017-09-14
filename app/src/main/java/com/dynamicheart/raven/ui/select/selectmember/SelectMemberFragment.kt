package com.dynamicheart.raven.ui.select.selectmember

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.ui.base.BaseFragment
import com.dynamicheart.raven.util.ToastHelper
import com.dynamicheart.raven.util.andorid.RecyclerItemClickListener
import javax.inject.Inject

class SelectMemberFragment: BaseFragment(), SelectMemberContract.View {

    @Inject lateinit var presenter: SelectMemberPresenter
    @Inject lateinit var selectableMembersAdapter: SelectableMembersAdapter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.button_select) lateinit var buttonSelect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_member, container, false)
        ButterKnife.bind(this, view)

        recyclerView.adapter = selectableMembersAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(activity, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val member = selectableMembersAdapter.members[position]
                val memberId = member.userId
                if(memberId != null){
                    val status = selectableMembersAdapter.selection[memberId]?:true
                    selectableMembersAdapter.selection[memberId] = !status
                    selectableMembersAdapter.notifyItemChanged(position)
                }
            }

            override fun onLongItemClick(view: View?, position: Int) {
                val member = selectableMembersAdapter.members[position]
                val memberId = member.userId
                if(memberId != null){
                    val status = selectableMembersAdapter.selection[memberId]?:true
                    selectableMembersAdapter.selection[memberId] = !status
                    selectableMembersAdapter.notifyItemChanged(position)
                }
            }
        }))

        buttonSelect.setOnClickListener {
            presenter.saveSelection(selectableMembersAdapter.members, selectableMembersAdapter.selection)
            activity.finish()
        }

        presenter.attachView(this)
        presenter.loadMembers()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showMembers(members: List<Member>) {
        selectableMembersAdapter.members = members
        members.forEach {
            val memberId = it.userId
            if(memberId != null){
                selectableMembersAdapter.selection[memberId]  = false
            }
        }

        selectableMembersAdapter.notifyDataSetChanged()
    }

    override fun showMembersEmpty() {
        selectableMembersAdapter.members = emptyList()
        selectableMembersAdapter.selection = emptyMap<String, Boolean>().toMutableMap()
        selectableMembersAdapter.notifyDataSetChanged()
        toastHelper.showShortToast(R.string.select_empty_members)
    }

    override fun showError() {
        toastHelper.showShortToast(R.string.error_loading_members)
    }
}