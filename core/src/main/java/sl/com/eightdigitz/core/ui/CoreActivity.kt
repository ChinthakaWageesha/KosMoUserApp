package sl.com.eightdigitz.core.ui

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sl.com.eightdigitz.navigation.features.AuthenticationNavigation
import sl.com.eightdigitz.navigation.features.MainNavigation
import org.koin.android.ext.android.inject
import sl.com.eightdigitz.core.injectFeature
import sl.com.eightdigitz.navigation.features.RequestCodes.AUTH
import sl.com.eightdigitz.navigation.features.RequestCodes.MAIN
import sl.com.eightdigitz.navigation.features.RequestCodes.SPLASH
import sl.com.eightdigitz.navigation.features.ResultCode.RESULT_NAV_LOGOUT
import sl.com.eightdigitz.navigation.features.ResultCode.RESULT_NAV_MAIN
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.Constant.ACTION_UNAUTH
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.cleaAll
import sl.com.eightdigitz.presentation.extensions.getAuthReference
import sl.com.eightdigitz.presentation.extensions.getIdToken

class CoreActivity : AppCompatActivity() {

    private val sharedPreferences by inject<SharedPreferences>()
    private val tokenAuthenticator by inject<SupportInterceptor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
        handleLogin()
    }

    private fun handleLogin() {

        val isNotificationShowing =
            intent.getBooleanExtra(IntentParsableConstants.EXTRA_NOTIFICATION, false)
        val notificationData =
            intent.getStringExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA)

        when {
            isNotificationShowing -> startMainWithNotifications(notificationData)

            intent.action == ACTION_UNAUTH -> {
                sharedPreferences.cleaAll()
                startLogin()
            }
            else -> checkIsLoggedIn()
        }
    }

    private fun checkIsLoggedIn() {
        if (!sharedPreferences.getIdToken().isNullOrEmpty()) {
            tokenAuthenticator.idToken = sharedPreferences.getIdToken()
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

    private fun startMainWithNotifications(mNotificationData: String?) {
        tokenAuthenticator.idToken = sharedPreferences.getIdToken()
        MainNavigation.dynamicStart?.let {
            it.putExtra(IntentParsableConstants.EXTRA_NOTIFICATION, true)
            it.putExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA, mNotificationData)
            startActivityForResult(it, MAIN)
        }
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
