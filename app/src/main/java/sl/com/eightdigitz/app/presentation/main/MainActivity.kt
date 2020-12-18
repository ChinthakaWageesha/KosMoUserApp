package sl.com.eightdigitz.app.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.di.injectFeature
import sl.com.eightdigitz.core.CoreActivity
import sl.com.eightdigitz.core.presentation.base.BaseActivity
import sl.com.eightdigitz.core.presentation.model.PUser
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.notifications.presentation.models.PCustomNotificationModel
import sl.com.eightdigitz.notifications.presentation.service.FcmService
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.alert
import sl.com.eightdigitz.presentation.extensions.jsonStringMapTo
import sl.com.eightdigitz.presentation.extensions.setActionBar
import sl.com.eightdigitz.presentation.extensions.showToast
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_app_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainVm by viewModel<MainViewModule>()
    private lateinit var user: PUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
        setContentView(R.layout.activity_app_main)

        mainVm.liveData.observe(this, Observer { logoutUser(it) })

        user = mainVm.getUser()!!

        handleFcmNotifications()

        btn_logout.setOnClickListener {
            alert("You Are About to Logout!", "Do you wish to continue?")
            {
                positiveButton("Yes") {
                    mainVm.logoutUser()
                }
                negativeButton("No") {

                }
            }
        }

        supportActionBar?.setActionBar(
            this, "Home", isHomeUpEnables = false, isCenterTitle = true
        )
    }

    private fun handleFcmNotifications() {
        val isShowNotifications =
            intent.getBooleanExtra(IntentParsableConstants.EXTRA_NOTIFICATION, false)

        val mNotificationData =
            intent.getStringExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA)
        mNotificationData?.let {
            try {
                // TODO if the notification has a custom data model use this (PCustomNotificationModel) to represent it
                Log.e("______****//**", it)

                val mCustomModel = it.jsonStringMapTo<PCustomNotificationModel>()
                Log.e("______******", mCustomModel.title)

                // TODO or else   use this default model
//                val mDefaultModel = it.jsonStringMapTo<PNotification>()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    //private fun getCurrentFragment() = supportFragmentManager.currentFragment(R.id.fl_main)

    private fun logoutUser(it: Resource<Success>) {
        when (it.state) {
            ResourceState.LOADING -> {
                showProgress()
            }
            ResourceState.SUCCESS -> {
                hideProgress()
                setResult(CoreActivity.RESULT_NAV_LOGOUT).also {
                    finish()
                }
            }
            ResourceState.ERROR -> {
                hideProgress()
                it.message?.showToast(this)
            }
        }
    }

    private fun updateTokenObserver() {
        val tokenObservable: Observable<String> = FcmService.getObservable()
        tokenObservable.subscribe(object : io.reactivex.Observer<String> {
            override fun onNext(token: String) {
                mainVm.logoutUser()
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                hideProgress()
            }

            override fun onSubscribe(s: Disposable) {
                println("onSubscribe")
//                showProgress()
            }
        })
    }
}
