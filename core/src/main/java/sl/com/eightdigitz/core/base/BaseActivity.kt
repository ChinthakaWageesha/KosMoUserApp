package sl.com.eightdigitz.core.base

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import sl.com.eightdigitz.core.ui.CoreActivity
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.ApiResponseCodes
import sl.com.eightdigitz.presentation.Constant.ACTION_UNAUTH
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.*
import org.koin.android.ext.android.inject
import sl.com.eightdigitz.core.model.domain.DUser

abstract class BaseActivity : AppCompatActivity() {

    private var progress: Dialog? = null
    private val mNetworkSupportInterceptor by inject<SupportInterceptor>()
    private val mSharedPreferences by inject<SharedPreferences>()
    var currentLoggedUser: DUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       progress = Dialog(this, R.style.progressBarStyle).apply {
           requestWindowFeature(Window.FEATURE_NO_TITLE)
           setContentView(sl.com.eightdigitz.presentation.R.layout.item_progress)
           setCancelable(true)
           setCanceledOnTouchOutside(true)
       }

        mNetworkSupportInterceptor.setAuthCallBackListener(object :
            SupportInterceptor.AuthenticatorCallBack {
            override fun onUnAuthorizedResponse(responseCode: Int?) {
                when (responseCode) {
                    ApiResponseCodes.UNAUTHORIZED -> {
                        handleResponse()
                    }
                    ApiResponseCodes.SERVER_ERROR -> {
                        runOnUiThread {
                            showAlert(message = Msg.ERROR_COMMON)
                        }
                    }
                }
            }
        })


        if(!mSharedPreferences.getUserString().isNullOrEmpty()){
            currentLoggedUser = mSharedPreferences.getUserString()?.jsonStringMapTo()
        }

        /*if (!mSharedPreferences.getIdToken().isNullOrEmpty()){
            mNetworkSupportInterceptor.idToken = mSharedPreferences.getIdToken()
        }*/
    }

    private fun handleResponse() {
        val idToken = mSharedPreferences.getIdToken()

        if (!idToken.isNullOrEmpty()) {
            runOnUiThread {
                getString(R.string.msg_session_expire).showToast(this)
            }
            val intent = Intent(this@BaseActivity, CoreActivity::class.java).apply {
                action = ACTION_UNAUTH
            }
            startActivity(intent)
            finishAffinity()
        }
    }

    fun showProgress() {
        hideProgress()
        progress?.let {
            if (!it.isShowing){
                it.show()
            }
        }
    }

    fun hideProgress() {
        progress?.let {
            if (it.isShowing){
                it.dismiss()
            }
        }
    }

    fun setBackground(drawableId: Int) {
        window.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this,
                drawableId
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
