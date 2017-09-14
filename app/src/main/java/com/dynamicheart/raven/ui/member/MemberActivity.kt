package com.dynamicheart.raven.ui.member

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.util.ToastHelper
import javax.inject.Inject

class MemberActivity: BaseActivity(),MemberContract.View {
    companion object {
        @JvmStatic val EXTRA_CURRENT_MEMBER_INDEX = "EXTRA_CURRENT_MEMBER_INDEX"
    }

    @Inject lateinit var presenter: MemberPresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.text_member_name)lateinit var textMemberName: TextView
    @BindView(R.id.text_member_id) lateinit var textMemberId: TextView
    @BindView(R.id.button_chat) lateinit var buttonChat: Button
    @BindView(R.id.button_expel) lateinit var buttonExpel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_member)
        ButterKnife.bind(this)

        presenter.attachView(this)
        presenter.loadMember(intent.getIntExtra(EXTRA_CURRENT_MEMBER_INDEX, 0))
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showMember(member: Member?, house: House?) {
        textMemberName.text = member?.username
        textMemberId.text = member?.userId
        buttonChat.setOnClickListener {  }
        if(AccountManager.user?.id == house?.founder?.id){
            buttonExpel.visibility = View.VISIBLE
            buttonExpel.setOnClickListener {
                presenter.expelMember(house?.id, member?.userId)
            }
        }
    }

    override fun showToast(resId: Int) {
        toastHelper.showShortToast(resId)
    }
}