package com.dynamicheart.raven.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.account.login.LoginFragment
import com.dynamicheart.raven.ui.account.signup.SignUpFragment
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.ui.main.MainActivity
import timber.log.Timber
import javax.inject.Inject

class AuthenticatorActivity : BaseActivity(), AuthenticatorContract.View {
    companion object {
        @JvmStatic private val KEY_PREFIX = AuthenticatorActivity::class.java.name + "."
        @JvmStatic private val EXTRA_ON_ADDED_INTENT = KEY_PREFIX + "on_added_intent"

        @JvmStatic
        fun makeIntent(onAddedIntent: Intent, context: Context): Intent {
            return Intent(context, AuthenticatorActivity::class.java)
                    .putExtra(EXTRA_ON_ADDED_INTENT, onAddedIntent)
        }
    }

    @Inject lateinit var presenter: AuthenticatorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_authenticator)
        ButterKnife.bind(this)

        presenter.attachView(this)
        showLoginFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, LoginFragment())
                .addToBackStack(LoginFragment::class.java.name)
                .commit()
    }

    override fun showSignUpFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .addToBackStack(SignUpFragment::class.java.name)
                .commit()
    }

    override fun onBackPressed() {
        Timber.i("%d fragments left currently", supportFragmentManager.backStackEntryCount)
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun goBackToPreviousActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}