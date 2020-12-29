package sl.com.eightdigitz.core.ui

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
import sl.com.eightdigitz.presentation.extensions.getUser

class CoreActivity : AppCompatActivity() {

    private val sharedPreferences by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mShowNotifications =
            intent.getBooleanExtra(IntentParsableConstants.EXTRA_NOTIFICATION, false)
        val mNotificationData =
            intent.getStringExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA)

        sharedPreferences.getUser().let {
            it.isNullOrEmpty().let { isNotLogin ->
                if (isNotLogin) {
                    startLogin()
                } else {
                    startMain()
                }
            }
        }

        /*when {
            mShowNotifications -> {
                startMainWithNotifications(mNotificationData)
            }
            intent.action == ACTION_UNAUTH -> {
                // TODO handle social login session here
                sharedPreferences.cleaAll()
                startLogin()
            }
            else start

        }*/
    }

    private fun startLogin() = AuthenticationNavigation.dynamicStart?.let {
        startActivityForResult(it, AUTH)
    }

    private fun startMain() = MainNavigation.dynamicStart?.let {
        //val pushNotification = intent.getParcelableExtra<NotificationMessage>(EXTRA_NOTIFICATION)
        it.action = intent.action
        //it.putExtra(EXTRA_NOTIFICATION, pushNotification)
        startActivityForResult(it, MAIN)
    }

    private fun startMainWithNotifications(mNotificationData: String?) =
        MainNavigation.dynamicStart?.let {
            it.putExtra(IntentParsableConstants.EXTRA_NOTIFICATION, true)
            it.putExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA, mNotificationData)
            startActivityForResult(it, RequestCodes.MAIN)
        }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == AUTH && resultCode == RESULT_NAV_MAIN -> {
                startMain()
            }
            //requestCode == AUTH && resultCode == RESULT_NAV_USER_SET_UP -> startUserSetUp()
            requestCode == MAIN && resultCode == RESULT_NAV_LOGOUT -> startLogin()
            requestCode == USER_SET && resultCode == RESULT_NAV_USER_SET_UP -> startMain()
            else -> println("Nothing to load :) ")
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == RequestCodes.SPLASH && resultCode == Activity.RESULT_OK -> {
                if (sharedPreferences.getToken().isNullOrEmpty()) {
                    startLogin()
                } else {
                    startMain()
                }
            }

            requestCode == RequestCodes.LOGIN -> {
                if (resultCode == RESULT_NAV_MAIN) {
                    startMain()
                } else
                    finish()
            }
            requestCode == RequestCodes.MAIN -> {
                if (resultCode == RESULT_NAV_LOGIN) {
                    startLogin()
                } else
                    finish()
            }
            else ->
                println("Nothing to load :) ")
        }
    }*/

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val AUTH = 101
        const val MAIN = 102
        const val USER_SET = 103

        const val RESULT_NAV_MAIN = 111
        const val RESULT_NAV_LOGOUT = 113
        const val RESULT_NAV_USER_SET_UP = 114
        const val ACTION_UNAUTH = "action_unauth"

        fun startActivity(context: Context) {
            val intent = Intent(context, CoreActivity::class.java).apply {
                action = ACTION_UNAUTH
            }
            context.startActivity(intent)
        }
    }
}
