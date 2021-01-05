package sl.com.eightdigitz.app.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.di.injectFeature
import sl.com.eightdigitz.core.ui.CoreActivity
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.models.Success
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
import sl.com.eightdigitz.navigation.features.ResultCode.RESULT_NAV_LOGOUT

class MainActivity : BaseActivity() {

    private val mainVm by viewModel<MainViewModule>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
        setContentView(R.layout.activity_app_main)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
