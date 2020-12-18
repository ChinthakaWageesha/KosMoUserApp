package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_get_phone_number.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.presentation.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.*

class GetPhoneNumberActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_phone_number)
        init()
    }

    private fun init() {
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

        startActivity<VerifyOTPActivity>()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send_code -> onSendCode()
        }
    }

}