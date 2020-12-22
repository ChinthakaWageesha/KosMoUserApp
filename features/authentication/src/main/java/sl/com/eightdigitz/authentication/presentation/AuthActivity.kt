package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import android.view.View
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.di.injectFeature
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.navigation.features.ResultCode
import kotlinx.android.synthetic.main.activity_auth.*
import sl.com.eightdigitz.authentication.presentation.verifyOTP.GetOTPActivity
import sl.com.eightdigitz.presentation.extensions.startActivity

class AuthActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        injectFeature()
        init()
    }

    private fun init() {
        btn_sinhala.setOnClickListener(this)
        btn_tamil.setOnClickListener(this)
        btn_english.setOnClickListener(this)
    }


    fun authSuccess() {
        setResult(ResultCode.RESULT_NAV_MAIN).also {
            finish()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sinhala -> {
                startActivity<GetOTPActivity>()

            }
            R.id.btn_tamil -> {
                startActivity<GetOTPActivity>()
            }
            R.id.btn_english -> {
                startActivity<GetOTPActivity>()

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}