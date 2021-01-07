package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.di.injectFeature
import sl.com.eightdigitz.authentication.presentation.registration.PostRegister
import sl.com.eightdigitz.authentication.presentation.registration.RegisterUser
import sl.com.eightdigitz.authentication.presentation.verifyOTP.GetOTP
import sl.com.eightdigitz.authentication.presentation.verifyOTP.VerifyOTP
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.extensions.currentFragment
import sl.com.eightdigitz.presentation.extensions.replaceFragment

class AuthActivity : BaseActivity() {

    var language: String? = null
    var mobileNumber: String? = null
    var fullName: String? = null
    var avatarUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        injectFeature()
        setChooseLanguage()
    }

    fun authSuccess(){
        setResult(ResultCode.RESULT_NAV_MAIN).also {
            finish()
        }
    }

    private fun setChooseLanguage(){
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            ChooseLanguage.newInstance(),
            ChooseLanguage.TAG
        )
    }

    fun setGetStarted(){
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            GetStarted.newInstance(),
            GetStarted.TAG
        )
    }

    fun setGetOTP(){
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            GetOTP.newInstance(),
            GetOTP.TAG
        )
    }

    fun setVerifyOTP(){
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            VerifyOTP.newInstance(),
            VerifyOTP.TAG
        )
    }

    fun setRegister(){
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            RegisterUser.newInstance(),
            RegisterUser.TAG
        )
    }
    
    fun setPostRegister(){
        supportFragmentManager.replaceFragment(
            R.id.fl_auth,
            PostRegister.newInstance(),
            PostRegister.TAG
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.currentFragment(R.id.fl_auth) is ChooseLanguage){
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }
}