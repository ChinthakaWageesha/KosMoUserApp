package sl.com.eightdigitz.core.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sl.com.eightdigitz.navigation.features.AuthenticationNavigation
import sl.com.eightdigitz.navigation.features.MainNavigation
import sl.com.eightdigitz.navigation.features.RequestCodes
import sl.com.eightdigitz.presentation.IntentParsableConstants
import org.koin.android.ext.android.inject
import sl.com.eightdigitz.navigation.features.RequestCodes.AUTH
import sl.com.eightdigitz.navigation.features.RequestCodes.MAIN
import sl.com.eightdigitz.navigation.features.RequestCodes.SPLASH
import sl.com.eightdigitz.navigation.features.ResultCode.RESULT_NAV_LOGOUT
import sl.com.eightdigitz.navigation.features.ResultCode.RESULT_NAV_MAIN
import sl.com.eightdigitz.presentation.Constant.ACTION_UNAUTH
import sl.com.eightdigitz.presentation.extensions.cleaAll
import sl.com.eightdigitz.presentation.extensions.getAccessToken
import sl.com.eightdigitz.presentation.extensions.getUser

class CoreActivity : AppCompatActivity() {

    private val sharedPreferences by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleLogin()
    }

    private fun handleLogin() {
        when (intent.action) {
            ACTION_UNAUTH -> {
                sharedPreferences.cleaAll()
                startLogin()
            }
            else -> checkIsLoggedIn()
        }
    }

    private fun checkIsLoggedIn() {
        if (!sharedPreferences.getAccessToken().isNullOrEmpty()) {
            startMain()
        } else {
            startLogin()
        }
    }

    private fun startLogin() = AuthenticationNavigation.dynamicStart?.let {
        startActivityForResult(it, AUTH)
    }

    private fun startMain() = MainNavigation.dynamicStart?.let {
        startActivityForResult(it, MAIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == SPLASH && resultCode == Activity.RESULT_OK -> checkIsLoggedIn()
            requestCode == AUTH && resultCode == RESULT_NAV_MAIN -> startMain()
            requestCode == MAIN && resultCode == RESULT_NAV_LOGOUT -> startLogin()
            else ->
                println("Nothing to load :) ")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
