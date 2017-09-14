package com.dynamicheart.raven.ui.account.signup

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.user.form.CreateUserForm
import com.dynamicheart.raven.ui.account.AuthenticatorActivity
import com.dynamicheart.raven.ui.base.BaseFragment
import javax.inject.Inject

class SignUpFragment: BaseFragment(), SignUpContract.View {

    @Inject lateinit var presenter: SignUpPresenter

    @BindView(R.id.edit_text_sign_up_username) lateinit var editTextSignUpUsername: TextInputEditText
    @BindView(R.id.edit_text_sign_up_email) lateinit var editTextSignUpEmail: TextInputEditText
    @BindView(R.id.edit_text_sign_up_phone) lateinit var editTextSignUpPhone: TextInputEditText
    @BindView(R.id.edit_text_sign_up_password) lateinit var editTextSignUpPassword: TextInputEditText
    @BindView(R.id.edit_text_sign_up_password_confirm) lateinit var editTextSignUpPasswordConfirm: TextInputEditText
    @BindView(R.id.button_sign_up) lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_sign_up, container, false)
        ButterKnife.bind(this, view)

        buttonSignUp.setOnClickListener({
            val password = editTextSignUpPassword.text.toString()
            val passwordConfirm = editTextSignUpPasswordConfirm.text.toString()

            if(password != passwordConfirm){
                presenter.showPasswordInconsistentError()
                return@setOnClickListener
            }

            val createUserForm =  CreateUserForm(
                    editTextSignUpUsername.text.toString(),
                    editTextSignUpPassword.text.toString(),
                    editTextSignUpPhone.text.toString(),
                    editTextSignUpEmail.text.toString()
            )

            presenter.signUp(createUserForm)
        })
        presenter.attachView(this)
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