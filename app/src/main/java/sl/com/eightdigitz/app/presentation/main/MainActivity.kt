package sl.com.eightdigitz.app.presentation.main

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.di.injectFeature
import sl.com.eightdigitz.app.presentation.activity.ActivityFragment
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.app.presentation.explore.Explore
import sl.com.eightdigitz.app.presentation.home.HomeFragment
import sl.com.eightdigitz.app.presentation.profile.ProfileFragment
import sl.com.eightdigitz.app.presentation.search.SearchFragment
import sl.com.eightdigitz.core.model.presentation.PNotification
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: BottomNavigationView
    private var pNotification: PNotification? = null
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
        setContentView(R.layout.activity_app_main)
        init()
    }

    private fun init() {
        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(this)
        navView.itemIconTintList = null
        onNavigationItemSelected(navView.menu.findItem(R.id.nav_home))
    }

    private fun handleFCM() {
        val isNotificationShow =
            intent.getBooleanExtra(IntentParsableConstants.EXTRA_NOTIFICATION, false)
        val notificationData =
            intent.getStringExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA)

        notificationData?.let {
            try {

                // TODO if the notification has a custom data model use this (PCustomNotificationModel) to represent it
                pNotification = it.jsonStringMapTo()


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (isNotificationShow) {
            pNotification?.entity_title!!.showToast(this)
            pNotification?.entity_id.toString().showToast(this)
        }
    }

    private fun getCurrentFragment() = supportFragmentManager.currentFragment(R.id.fl_main)

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                if (getCurrentFragment() !is HomeFragment) {
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        HomeFragment.newInstance(),
                        HomeFragment.TAG
                    )
                }
            }
            R.id.nav_search -> {
                if (getCurrentFragment() !is SearchFragment) {
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        SearchFragment.newInstance(),
                        SearchFragment.TAG
                    )
                }
            }
            R.id.nav_explore -> {
                startActivity<Explore>()
            }
            R.id.nav_activity -> {
                if (getCurrentFragment() !is ActivityFragment) {
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        ActivityFragment.newInstance(),
                        ActivityFragment.TAG
                    )
                }
            }
            R.id.nav_profile -> {
                if (getCurrentFragment() !is ProfileFragment) {
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        ProfileFragment.newInstance(),
                        ProfileFragment.TAG
                    )
                }
            }
        }
        return true
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (doubleBackToExitPressedOnce) {
          finishAffinity()
          return
        }

        this.doubleBackToExitPressedOnce = true
        "Please click BACK again to exit".showToast(this)

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)

        if (count == 1) {
            finishAffinity()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
