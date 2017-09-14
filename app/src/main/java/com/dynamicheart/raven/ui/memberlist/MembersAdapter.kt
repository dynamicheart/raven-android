package com.dynamicheart.raven.ui.memberlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.member.Member
import javax.inject.Inject

class MembersAdapter
@Inject constructor(): RecyclerView.Adapter<MembersAdapter.MemberViewHolder>(){

    var members = emptyList<Member>()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_member, parent, false)
        context = parent.context
        return MemberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        holder.textMemberName.text = member.username
        holder.textMemberRole.text = context.getString(getRoleName(member.role))
    }

    override fun getItemCount(): Int = members.size

    private fun getRoleName(role: Int?): Int{
        return when(role){
            1 -> R.string.role_maester
            2 -> R.string.role_lord
            else -> R.string.role_ordinary
        }
    }

    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_member_role) lateinit var textMemberRole: TextView
        @BindView(R.id.text_member_name) lateinit var textMemberName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}