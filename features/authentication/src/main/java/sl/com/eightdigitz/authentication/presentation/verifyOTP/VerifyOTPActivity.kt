package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_verify_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*

class VerifyOTPActivity : BaseActivity(), View.OnClickListener {

    private val vmOTPToken by viewModel<OTPViewModel>()

    companion object {
        fun startActivity(context: Context, action: String) {
            val intent = Intent(context, VerifyOTPActivity::class.java).apply {
                this.action = action
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        init()
    }

    private fun init() {
        et_verify_code.validate(isCheckValidateIcon = true) { s -> s.length > 4 }
        vmOTPToken.liveDataOTPToken.observe(this, Observer { observerOTPToken(it) })
        btn_verify.setOnClickListener(this)
    }

    private fun onVerify() {
        val isOTPCode =
            et_verify_code.validateOnTextChange(
                "",
                isCheckValidateIcon = true
            ) { s -> s.length > 4 }

        if (!isOTPCode) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_invalid_verify_code))
            return
        }
        verifyCode()
    }

    @SuppressLint("MissingPermission")
    private fun verifyCode() {
        withNetwork({
            vmOTPToken.getOTPToken(intent.action!!, et_verify_code.getStringTrim())
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    private fun observerOTPToken(resource: Resource<DOTPToken>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    it.data?.idToken?.showToast(this)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_verify -> onVerify()
        }
    }
}