package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.auth0.android.Auth0
import kotlinx.android.synthetic.main.activity_get_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*

class GetOTPActivity : BaseActivity(), View.OnClickListener {

    private var auth0: Auth0? = null
    private val vmOTP by viewModel<OTPViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)
        init()
    }

    private fun init() {
        auth0 = Auth0(this)
        auth0?.isOIDCConformant = true
        vmOTP.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        et_mobile.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidPhone() }
        btn_send_code.setOnClickListener(this)
    }

    private fun onSendCode() {
        val isPhone =
            et_mobile.validateOnTextChange("", isCheckValidateIcon = true) { s -> s.isValidPhone() }

        if (!isPhone) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_invalid_phone))
            return
        }

        getOtp()
    }

    @SuppressLint("MissingPermission")
    private fun getOtp() {
        withNetwork({
            vmOTP.getOTP("+94715781989")
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    private fun observerGetOTP(resource: Resource<DOTP>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    navigateToVerify()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun navigateToVerify() {
        VerifyOTPActivity.startActivity(this, "+94715781989")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send_code -> onSendCode()
        }
    }
}