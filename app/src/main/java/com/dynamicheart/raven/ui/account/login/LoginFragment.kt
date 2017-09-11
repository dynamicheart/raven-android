package com.dynamicheart.raven.ui.account.login

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.user.form.LoginForm
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import com.dynamicheart.raven.ui.base.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginContract.View {

    companion object {
        @JvmStatic val LOGIN_FRAGMENT_NAME = LoginFragment::class.java.name
    }

    @Inject lateinit var presenter: LoginPresenter

    @BindView(R.id.button_go_to_sign_up) lateinit var buttonGoToSignUp: Button
    @BindView(R.id.button_login) lateinit var buttonLogin: Button
    @BindView(R.id.edit_text_username) lateinit var editTextUsername: TextInputEditText
    @BindView(R.id.edit_text_password) lateinit var editTextPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this, view)

        buttonGoToSignUp.setOnClickListener({
            (activity as AuthenticatorActivity).showSignUpFragment()
        })

        buttonLogin.setOnClickListener({
            val loginForm = LoginForm(
                    editTextUsername.text.toString(),
                    editTextPassword.text.toString()
            )
            presenter.login(loginForm)
        })

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun goBackToPreviousActivity() {
        (activity as AuthenticatorActivity).goBackToPreviousActivity()
    }
}