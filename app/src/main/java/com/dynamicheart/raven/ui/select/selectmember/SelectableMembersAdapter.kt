package com.dynamicheart.raven.ui.select.selectmember

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.member.Member
import javax.inject.Inject

class SelectableMembersAdapter
@Inject constructor(): RecyclerView.Adapter<SelectableMembersAdapter.MemberViewHolder>(){

    var members = emptyList<Member>()
    var selection: MutableMap<String, Boolean> = emptyMap<String, Boolean>().toMutableMap()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_selectable_member, parent, false)
        context = parent.context
        return MemberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        val isSelected = selection[member.userId]?:false
        holder.textUserName.text = member.username
        holder.textUserRole.text = context.getString(getRoleName(member.role))
        holder.imageSelection.setImageResource(if(isSelected) R.drawable.ic_check_circle_yellow_18dp else R.drawable.ic_remove_circle_outline_black_24dp)
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

        @BindView(R.id.image_selection) lateinit var imageSelection: ImageView
        @BindView(R.id.image_user_avatar) lateinit var imageUserAvatar: ImageView
        @BindView(R.id.text_user_name) lateinit var textUserName: TextView
        @BindView(R.id.text_member_role) lateinit var textUserRole: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}