package com.dynamicheart.raven.ui.account

import android.content.Context
import android.content.Intent
import com.dynamicheart.raven.ui.account.login.LoginFragment
import com.dynamicheart.raven.ui.base.BaseActivity

class AuthenticatorActivity: BaseActivity() {
    companion object {
        @JvmStatic private val KEY_PREFIX = LoginFragment::class.java.name + "."
        @JvmStatic private val EXTRA_ON_ADDED_INTENT = KEY_PREFIX + "on_added_intent"

        @JvmStatic fun makeIntent(onAddedIntent: Intent, context: Context): Intent {
            return Intent(context, LoginFragment::class.java)
                    .putExtra(EXTRA_ON_ADDED_INTENT, onAddedIntent)
        }
    }
}