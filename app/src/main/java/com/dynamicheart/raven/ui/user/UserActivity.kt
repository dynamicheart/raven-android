package com.dynamicheart.raven.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.main.MainActivity
import com.dynamicheart.raven.util.ToastHelper
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject

class UserActivity: BaseActivity(), UserContract.View {

    @Inject lateinit var presenter: UserPresenter
    @Inject lateinit var toastHelper: ToastHelper

    @BindView(R.id.text_user_name)lateinit var textUserName: TextView
    @BindView(R.id.image_user_avatar) lateinit var imageUserAvatar: CircleImageView
    @BindView(R.id.text_user_description) lateinit var textUserEmail: TextView
    @BindView(R.id.text_house_founder_name) lateinit var textUserPhone: TextView
    @BindView(R.id.button_logout) lateinit var buttonLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_user)
        ButterKnife.bind(this)

        buttonLogout.setOnClickListener{
            presenter.logout()
        }

        presenter.attachView(this)
        presenter.loadUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showUserInfo(user: User?) {
        textUserName.text = user?.username
        textUserEmail.text = user?.email
        textUserPhone.text = user?.phoneNumber
    }

    override fun showCheckoutError() {
        toastHelper.showShortToast(R.string.error_checkout)
    }

    override fun restartApplication() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        toastHelper.showShortToast(R.string.success_checkout)
    }
}