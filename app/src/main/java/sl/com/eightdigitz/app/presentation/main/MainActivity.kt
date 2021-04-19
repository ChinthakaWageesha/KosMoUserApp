package sl.com.eightdigitz.app.presentation.main

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.di.injectFeature
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.app.presentation.explore.Explore
import sl.com.eightdigitz.app.presentation.home.HomeFragment
import sl.com.eightdigitz.app.presentation.profile.ProfileFragment
import sl.com.eightdigitz.app.presentation.search.SearchFragment
import sl.com.eightdigitz.core.model.presentation.PNotification
import sl.com.eightdigitz.notifications.presentation.notification.NotificationsFragment
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
        handleFCM()
    }

    private fun handleFCM() {
        val isNotificationShow =
            intent.getBooleanExtra(IntentParsableConstants.EXTRA_NOTIFICATION, false)
        val notificationData =
            intent.getStringExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA)

        notificationData?.let {
            try {
                pNotification = it.jsonStringMapTo()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (isNotificationShow) {
            onNavigationItemSelected(navView.menu.findItem(R.id.nav_activity)).also {
                setNotifications()
            }
        }
    }

    private fun getCurrentFragment() = supportFragmentManager.currentFragment(R.id.fl_main)

    private fun setHome() {
        if (getCurrentFragment() !is HomeFragment) {
            supportFragmentManager.replaceFragment(
                R.id.fl_main,
                HomeFragment.newInstance(),
                HomeFragment.TAG
            )
        }
    }

    private fun setSearch() {
        if (getCurrentFragment() !is SearchFragment) {
            supportFragmentManager.replaceFragment(
                R.id.fl_main,
                SearchFragment.newInstance(),
                SearchFragment.TAG
            )
        }
    }

    private fun setNotifications() {
        if (getCurrentFragment() !is NotificationsFragment) {
            supportFragmentManager.replaceFragment(
                R.id.fl_main,
                NotificationsFragment.newInstance(),
                NotificationsFragment.TAG
            )
        }
    }

    private fun setProfile() {
        if (getCurrentFragment() !is ProfileFragment) {
            supportFragmentManager.replaceFragment(
                R.id.fl_main,
                ProfileFragment.newInstance(),
                ProfileFragment.TAG
            )
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> setHome()
            R.id.nav_search -> setSearch()
            R.id.nav_explore -> startActivity<Explore>()
            R.id.nav_activity -> setNotifications()
            R.id.nav_profile -> setProfile()
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
