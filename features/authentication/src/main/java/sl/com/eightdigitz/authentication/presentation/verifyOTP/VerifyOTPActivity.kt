package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verify_otp.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.validate

class VerifyOTPActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        init()
    }

    private fun init() {
        et_verify_code.validate(isCheckValidateIcon = true) { s -> s.length > 4 }
    }
}