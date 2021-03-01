package sl.com.eightdigitz.app.presentation.main

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.di.injectFeature
import sl.com.eightdigitz.core.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.presentation.activity.ActivityFragment
import sl.com.eightdigitz.app.presentation.explore.ExploreFragment
import sl.com.eightdigitz.app.presentation.home.HomeFragment
import sl.com.eightdigitz.app.presentation.order.userOrders.UserOrders
import sl.com.eightdigitz.app.presentation.profile.ProfileFragment
import sl.com.eightdigitz.app.presentation.search.SearchFragment
import sl.com.eightdigitz.presentation.extensions.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: BottomNavigationView
    private val vm by viewModel<MainViewModel>()

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
                if (getCurrentFragment() !is ExploreFragment) {
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        ExploreFragment.newInstance(),
                        ExploreFragment.TAG
                    )
                }
            }
            R.id.nav_activity -> {
                startActivity<UserOrders>()
                /*if (getCurrentFragment() !is ActivityFragment) {
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        ActivityFragment.newInstance(),
                        ActivityFragment.TAG
                    )
                }*/
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

        /*if (doubleBackToExitPressedOnce) {
          finishAffinity()
          return
        }

        this.doubleBackToExitPressedOnce = true
        "Please click BACK again to exit".showToast(this)

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)*/

        if (count == 1) {
            finishAffinity()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
