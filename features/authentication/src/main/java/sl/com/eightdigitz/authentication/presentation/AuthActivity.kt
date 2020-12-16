package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import android.view.View
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.di.injectFeature
import sl.com.eightdigitz.authentication.presentation.forgotpassword.ForgotPassword
import sl.com.eightdigitz.authentication.presentation.login.LoginFragment
import sl.com.eightdigitz.authentication.presentation.registration.RegistrationFragment
import sl.com.eightdigitz.authentication.presentation.resetpassword.ResetPasswordFragment
import sl.com.eightdigitz.authentication.presentation.selectuser.SelectUserFragment
import sl.com.eightdigitz.core.presentation.base.BaseActivity
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.extensions.currentFragment
import sl.com.eightdigitz.presentation.extensions.replaceFragment
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : BaseActivity() {

    var userType: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        injectFeature()
        setSelectUser()
    }

    override fun progressBar(): View? = progressBar

    fun authSuccess() {
        setResult(ResultCode.RESULT_NAV_MAIN).also {
            finish()
        }
    }

    fun setSignIn() {
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            LoginFragment.newInstance(),
            LoginFragment.TAG
        )
    }

    fun setSelectUser() {
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            SelectUserFragment.newInstance(),
            SelectUserFragment.TAG
        )
    }

    fun setResetPassword() {
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            ResetPasswordFragment.newInstance(),
            ResetPasswordFragment.TAG
        )
    }

    fun setSignUp() {
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            RegistrationFragment.newInstance(),
            RegistrationFragment.TAG
        )
    }

    fun setForgotPassword() {
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            ForgotPassword.newInstance(),
            ForgotPassword.TAG
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.currentFragment(R.id.fl_auth) is SelectUserFragment) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }
}