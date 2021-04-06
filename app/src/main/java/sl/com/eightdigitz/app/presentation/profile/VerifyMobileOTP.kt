package sl.com.eightdigitz.app.presentation.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_verify_mobile_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class VerifyMobileOTP : BaseActivity(), View.OnClickListener {

    private val vmProfile by viewModel<ProfileViewModel>()
    private var mobileNo94: String? = null
    private var isVerifyEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_mobile_otp)
        setToolbar()
        getData()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = true
        )
    }

    private fun getData(){
        if (intent.hasExtra(IntentParsableConstants.EXTRA_MOBILE_NUMBER)){
            mobileNo94 = intent.getStringExtra(IntentParsableConstants.EXTRA_MOBILE_NUMBER)
        }
    }

    private fun init() {
        getOTP()
        vmProfile.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        vmProfile.liveDataOTPToken.observe(this, Observer { observerOTPToken(it) })

        et_verify_code.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text?.length!! > 6) {
                    et_verify_code.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length == 6 }
                }

                if (text.length == 6) {
                    makeVerifyEnable()
                } else {
                    makeVerifyDisable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        cl_verify.setOnClickListener(this)
        tv_message_not_receive_code.setOnClickListener(this)
    }

    private fun makeVerifyEnable() {
        ibtn_verify.setEnable()
        tv_verify.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorWhite
            )
        )
        isVerifyEnable = true
    }

    private fun makeVerifyDisable() {
        ibtn_verify.setDisable()
        tv_verify.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorPinkAlpha30
            )
        )
        isVerifyEnable = false
    }

    private fun onVerify() {
        if (isVerifyEnable) {
            verifyCode()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun countDownTimer() {
        tv_message_not_receive_code.text =
            getString(sl.com.eightdigitz.presentation.R.string.message_did_not_receive_code)
        tv_timer.text = "60.00s"
        tv_timer.visibility = View.VISIBLE

        object : CountDownTimer(60000, 1000) {


            override fun onTick(millisUntilFinished: Long) {
                if (tv_timer != null) {
                    tv_timer.text = "${millisUntilFinished / 1000}s"
                }
            }

            override fun onFinish() {
                if (tv_message_not_receive_code != null && tv_timer != null) {
                    tv_message_not_receive_code.text = "Resend Code"
                    tv_timer.visibility = View.GONE
                }
            }

        }.start()
    }

    @SuppressLint("MissingPermission")
    private fun getOTP() {
        et_verify_code.requestFocus()
        withNetwork({
            vmProfile.getOTP(mobileNo94!!)
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    @SuppressLint("MissingPermission")
    private fun verifyCode() {
        withNetwork({
            vmProfile.getOTPToken(mobileNo94!!, et_verify_code.getStringTrim())
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
                    countDownTimer()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerOTPToken(resource: Resource<DOTPToken>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setResult(ResultCodes.OTP_RESULT_CODE).also {
                        finish()
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    "Wrong phone number or verification code.".showToast(this)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_verify -> onVerify()
            R.id.tv_message_not_receive_code -> {
                if (tv_message_not_receive_code.text == "Resend Code") {
                    et_verify_code.clearText()
                    getOTP()
                }
            }
        }
    }

    override fun onResume() {
        countDownTimer()
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_gradient_brown_pink_purple)
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}