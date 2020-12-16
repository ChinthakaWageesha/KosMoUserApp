package sl.com.eightdigitz.core.presentation.base

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import sl.com.eightdigitz.core.CoreActivity
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.ApiResponseCodes
import sl.com.eightdigitz.presentation.Constant.ACTION_UNAUTH
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.*
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    private val mNetworkSupportInterceptor by inject<SupportInterceptor>()
    private val mSharedPreferences by inject<SharedPreferences>()
    var mProgressBar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNetworkSupportInterceptor.setAuthCallBackListner(object :
            SupportInterceptor.AuthenticatorCallBack {
            override fun onUnAuthorizedResponse(responseCode: Int?) {
                when (responseCode) {
                    ApiResponseCodes.UNAUTHORIZED -> {
                        handleResponse()
                    }
                    ApiResponseCodes.SERVER_ERROR -> {
                        runOnUiThread {
                            showAlert(Msg.ERROR_COMMON)
                        }
                    }
                }
            }
        })

        mProgressBar = progressBar()
    }

    private fun handleResponse() {
        val mAccessToken = mSharedPreferences.getToken()

        if (!mAccessToken.isNullOrEmpty()) {
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
        progressBar()?.visible()
    }

    fun hideProgress() {
        progressBar()?.gone()
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

    abstract fun progressBar(): View?
}
